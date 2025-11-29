package com.erp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportService {

  public void generateReport() {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    BufferedWriter writer = null;
    String querry = "SELECT s.id, p.name, s.quantity, s.date, p.price " + "FROM sales s JOIN products p ON s.product_id = p.id";
    try {
      con = DB.getConnection();
      stmt = con.prepareStatement(querry);
      rs = stmt.executeQuery();

      writer = new BufferedWriter(new FileWriter("repot.csv"));

      writer.write("sales Id,product name,quantity,date,price");
      writer.newLine();

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy hh:mm:ss");

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        Timestamp ts = rs.getTimestamp("date");
        LocalDateTime date = ts.toLocalDateTime();
        writer.write(id+","+name+","+quantity+","+date.format(formatter)+","+price);
        writer.newLine();
      }
      System.out.println("The report is saves as report.csv");
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    } finally {
            if (writer != null) try { writer.close(); } catch (IOException ignored) {}
            if (rs != null) try { rs.close(); } catch (Exception ignored) {}
            if (stmt != null) try { stmt.close(); } catch (Exception ignored) {}
            if (con != null) try { con.close(); } catch (Exception ignored) {}
        }
  }
}
