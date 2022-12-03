package com.skm.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hibernate-complete-guide?useSSL=false&serverTimezone=UTC";
        String userName = "hibernate-complete-guide";
        String password = "hibernate-complete-guide";
        try {
            System.out.println("Connecting to database");
            Connection conn = DriverManager.getConnection(jdbcUrl,userName,password);
            System.out.println("Connection was Successful!");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
