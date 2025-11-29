package com.erp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PurchaseService {
    public void purchase(int id, int qty) throws Exception {
        Connection con = null;
        PreparedStatement upd = null;
        PreparedStatement ins = null;
        try {
            con = DB.getConnection();
            con.setAutoCommit(false);

            upd = con.prepareStatement("UPDATE products SET quantity = quantity + ? WHERE id = ?");
            upd.setInt(1, qty);
            upd.setInt(2, id);
            int rows = upd.executeUpdate();
            if (rows != 1) {
                con.rollback();
                throw new SQLException("Product id " + id + " not found.");
            }

            ins = con.prepareStatement("INSERT INTO purchases(product_id, quantity) VALUES(?, ?)");
            ins.setInt(1, id);
            ins.setInt(2, qty);
            ins.executeUpdate();

            con.commit();
            System.out.println("Purchase recorded.");
        } catch (SQLException e) {
            if(e.getMessage().equals("Product id " + id + " not found.")) {
              System.out.println("Item with the id " + id + " does not exist. Please list the item before purchase");
              return;
            }
            if (con != null) try { con.rollback(); } catch (SQLException ignored) {}
            throw e;
        } finally {
            if (upd != null) try { upd.close(); } catch (SQLException ignored) {}
            if (ins != null) try { ins.close(); } catch (SQLException ignored) {}
            if (con != null) try { con.setAutoCommit(true); con.close(); } catch (SQLException ignored) {}
        }
    }
}
