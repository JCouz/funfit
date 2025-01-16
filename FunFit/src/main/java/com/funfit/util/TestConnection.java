package com.funfit.util;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Database connection failed!");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Database connection closed.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}