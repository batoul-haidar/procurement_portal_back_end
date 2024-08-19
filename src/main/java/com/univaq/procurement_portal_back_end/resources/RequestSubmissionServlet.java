/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 ** @author BATOUL
 */
public class RequestSubmissionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category_id = request.getParameter("category_id");
        String productType_id = request.getParameter("product_type_id");
        String product_id = request.getParameter("product_id");
        String features = request.getParameter("features");
        String additionalReg = request.getParameter("additional_req");
        String quantity = request.getParameter("quantity");
        
        
        // Validate the parameters
        if (category_id == null || category_id.isEmpty() ||
            productType_id == null || productType_id.isEmpty() ||
            product_id == null || product_id.isEmpty() ||
            quantity == null || quantity.isEmpty()) {
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Missing or invalid parameters\"}");
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection_old.getConnection();

            String sql = "{CALL submit_request(?, ?, ?, ?, ?, ?, 'pending')}";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(category_id));
            statement.setInt(2, Integer.parseInt(productType_id));
            statement.setInt(3, Integer.parseInt(product_id));
            statement.setString(4, features);
            statement.setString(5, additionalReg);
            statement.setInt(6, Integer.parseInt(quantity));

            statement.executeUpdate();

            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");

        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Invalid number format\"}");
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
