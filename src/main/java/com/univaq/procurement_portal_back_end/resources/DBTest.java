/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Batoul
 */
public class DBTest {
    
    public static void main(String[] args) {
        
        
        String url = "jdbc:mysql://localhost:3306/procurement_porat";  // Update with your DB details
        String user = "test_user";
        String password = "Proc@2024";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected to the database.");
            conn.close();  // Close the connection after testing
        } catch (SQLException e) {
            System.out.println("Unable to connect to the database.");
            e.printStackTrace();
        }
    }
    
}
