/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 ** @author BATOUL
 */
public class ListNewReqServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try (Connection conn = DatabaseConnection_old.getConnection()) {
            String query = "{call list_new_requests()}"; // Replace 'fetchProcedureName' with your actual stored procedure name
            try (CallableStatement stmt = conn.prepareCall(query);
                 ResultSet rs = stmt.executeQuery()) {
                
                StringBuilder jsonResult = new StringBuilder();
                jsonResult.append("[");
                boolean first = true;
                while (rs.next()) {
                    if (!first) {
                        jsonResult.append(",");
                    } else {
                        first = false;
                    }
                    jsonResult.append("{");
                    jsonResult.append("\"request_id\": ").append(rs.getInt("request_id")).append(",");
                    jsonResult.append("\"category\": \"").append(rs.getString("category")).append("\",");
                    jsonResult.append("\"product_type\": \"").append(rs.getString("product_type")).append("\",");
                    jsonResult.append("\"product\": \"").append(rs.getString("product")).append("\",");
                    jsonResult.append("\"features\": \"").append(rs.getString("features")).append("\",");
                    jsonResult.append("\"additional_requirements\": \"").append(rs.getString("additional_requirements")).append("\",");
                    jsonResult.append("\"quantity\": \"").append(rs.getString("quantity")).append("\",");
                    jsonResult.append("\"request_status\": \"").append(rs.getString("request_status")).append("\",");
                    jsonResult.append("\"request_date\": \"").append(rs.getString("request_date")).append("\"");
                    jsonResult.append("}");
                }
                jsonResult.append("]");

                response.getWriter().write(jsonResult.toString());
            }
        } catch (SQLException e) {
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

}