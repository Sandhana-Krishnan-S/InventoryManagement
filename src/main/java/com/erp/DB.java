package com.erp;

import java.sql.*;

public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/erp";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
