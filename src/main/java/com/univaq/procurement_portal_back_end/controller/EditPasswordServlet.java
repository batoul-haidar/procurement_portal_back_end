/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.univaq.procurement_portal_back_end.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univaq.procurement_portal_back_end.data.dao.UserDAO;
import com.univaq.procurement_portal_back_end.data.model.UserDAOImp;
import com.univaq.procurement_portal_back_end.resources.NotificationService;
import com.univaq.procurement_portal_back_end.resources.User;
import com.univaq.procurement_portal_back_end.security.SecurityHelpers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
* @author BATOUL
 */
public class EditPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NotificationService notificationService = new NotificationService();


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        

        int userId = json.get("user_id").getAsInt();
        String password = json.get("password").getAsString();
        

        
        // Validate the parameters
        if (
            password == null || password.isEmpty()) {      
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Missing or invalid parameters\"}");
            return;
        }

      

            try {               
                String hashedPassword = SecurityHelpers.getPasswordHashPBKDF2(password);
                UserDAO userDao = new UserDAOImp();
                
                response.setStatus(HttpServletResponse.SC_OK);
                userDao.updatePassword(userId,hashedPassword );
                response.getWriter().write("{\"status\":\"success\"}");
                


                notificationService.addNotification("New request added", "list_new_requests.html", null, "Technician");
              
                response.setContentType("application/json");
                

            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
            } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
