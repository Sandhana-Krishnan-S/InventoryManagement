package com.erp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesService {
    private ProductService ps = new ProductService();

    public void sell(int id, int qty) throws Exception {
        Connection con = null;
        PreparedStatement sel = null;
        PreparedStatement upd = null;
        PreparedStatement ins = null;
        ResultSet rs = null;

        try {
            con = DB.getConnection();
            con.setAutoCommit(false);

            sel = con.prepareStatement("SELECT quantity FROM products WHERE id = ? FOR UPDATE");
            sel.setInt(1, id);
            rs = sel.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Product id " + id + " not found.");
            }

            int currentQty = rs.getInt("quantity");
            if (currentQty < qty) {
                con.rollback();
                System.out.println("Not enough stock. Current: " + currentQty + ", requested: " + qty);
                return;
            }


            upd = con.prepareStatement("UPDATE products SET quantity = quantity - ? WHERE id = ?");
            upd.setInt(1, qty);
            upd.setInt(2, id);
            int rows = upd.executeUpdate();
            if (rows != 1) throw new SQLException("Failed to update product quantity for id " + id);


            ins = con.prepareStatement("INSERT INTO sales(product_id, quantity) VALUES(?, ?)");
            ins.setInt(1, id);
            ins.setInt(2, qty);
            ins.executeUpdate();

            con.commit();
            System.out.println("Sale recorded.");
        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ignored) {}
            throw e;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (sel != null) try { sel.close(); } catch (SQLException ignored) {}
            if (upd != null) try { upd.close(); } catch (SQLException ignored) {}
            if (ins != null) try { ins.close(); } catch (SQLException ignored) {}
            if (con != null) try { con.setAutoCommit(true); con.close(); } catch (SQLException ignored) {}
        }
    }
}

