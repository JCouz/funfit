package com.funfit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getConnection() {
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funfit", "root","root1234");
            
            System.out.println("FunFit connected");
        } catch (ClassNotFoundException e) {
        	 System.err.println("Error: MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
        	 System.err.println("Error: Database connection failed! Check URL, username, and password.");
            e.printStackTrace();
        }
        
        return con;
    }
}