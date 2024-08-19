/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.univaq.procurement_portal_back_end.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univaq.procurement_portal_back_end.data.dao.UserDAO;
import com.univaq.procurement_portal_back_end.data.model.UserDAOImp;
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
 ** @author BATOUL
 */
public class updateUserServlet extends HttpServlet {

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
        
        String fullName = json.get("user_full_name").getAsString();
        int userId = json.get("user_id").getAsInt();
        String userType = json.get("user_type").getAsString();
        String userDepartement = json.get("user_departement").getAsString();
        String otherDepartement = json.get("other_departement").getAsString();
        /*
        String full_name = request.getParameter("user_full_name");
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        String user_type = request.getParameter("user_type");
        String user_departement = request.getParameter("user_departement");
        String other_departement = request.getParameter("other_departement");
        */

        
        // Validate the parameters
        if (fullName == null || fullName.isEmpty() ||
            userType == null || userType.isEmpty() ||
            userDepartement == null || userDepartement.isEmpty()) {      
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Missing or invalid parameters\"}");
            return;
        }

      

            try {               
                User user = new User (userId, userType, fullName, userDepartement, otherDepartement);
                UserDAO userDao = new UserDAOImp();
                
                
              //  AddUserDAO addUser = new AddUserDAO();
              //  addUser.setUser(user_type, user_full_name, user_name, password, user_departement, other_departement);


                    response.setStatus(HttpServletResponse.SC_OK);
                    userDao.updateUser(user);
                    response.getWriter().write("{\"status\":\"success\"}");
                
                
                
              
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
