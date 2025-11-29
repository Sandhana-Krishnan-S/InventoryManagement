package com.erp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductService {

    public void addProduct(String name, double price, int qty) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DB.getConnection();
            stmt = con.prepareStatement("INSERT INTO products(name, price, quantity) VALUES(?,?,?)");
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, qty);
            stmt.executeUpdate();
            System.out.println("Product added.");
        }catch(Exception exception) {
          System.out.println("Error: " + exception.getMessage());
          exception.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (con != null) try { con.close(); } catch (SQLException ignored) {}
        }
    }

    public void listProducts() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DB.getConnection();
            stmt = con.prepareStatement("SELECT * FROM products");
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantity");
                System.out.println(id + " | " + name + " | " + price + " | QTY: " + qty);
            }
        } catch(Exception exception) {

          System.out.println("Error: " + exception.getMessage());
          exception.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (con != null) try { con.close(); } catch (SQLException ignored) {}
        }
    }
    public int getQty(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DB.getConnection();
            stmt = con.prepareStatement("SELECT quantity FROM products WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            } else {
                throw new SQLException("Product id " + id + " not found.");
            }
        } catch(Exception exception) {

          System.out.println("Error: " + exception.getMessage());
          exception.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (con != null) try { con.close(); } catch (SQLException ignored) {}
        }
        return 0;
    }

    public void updateQty(int id, int change) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DB.getConnection();
            stmt = con.prepareStatement("UPDATE products SET quantity = quantity + ? WHERE id = ?");
            stmt.setInt(1, change);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }catch(Exception exception){
          
          System.out.println("Error: " + exception.getMessage());
          exception.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (con != null) try { con.close(); } catch (SQLException ignored) {}
        }
    }
}

