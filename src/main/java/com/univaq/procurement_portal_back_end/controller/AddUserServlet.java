/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univaq.procurement_portal_back_end.data.dao.UserDAO;
import com.univaq.procurement_portal_back_end.data.model.UserDAOImp;
import com.univaq.procurement_portal_back_end.resources.User;
import com.univaq.procurement_portal_back_end.security.SecurityHelpers;

/**
 ** @author BATOUL
 */
public class AddUserServlet extends HttpServlet{
    
    
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        
        String fullName = json.get("user_full_name").getAsString();
        String userName = json.get("user_name").getAsString();
        String password = json.get("password").getAsString();
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
            userName == null || userName.isEmpty() ||
            password == null || password.isEmpty() ||
            userType == null || userType.isEmpty() ||
            userDepartement == null || userDepartement.isEmpty()) {      
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Missing or invalid parameters\"}");
            return;
        }

      

            try {               
                String hashedPassword = SecurityHelpers.getPasswordHashPBKDF2(password);
                User user = new User (fullName, userType, userName, hashedPassword, userDepartement, otherDepartement);
                UserDAO userDao = new UserDAOImp();
                
                
              //  AddUserDAO addUser = new AddUserDAO();
              //  addUser.setUser(user_type, user_full_name, user_name, password, user_departement, other_departement);

              
                boolean checkUsername = userDao.checkUserName(userName);
                
                if (checkUsername==false){
                    response.setStatus(HttpServletResponse.SC_OK);
                    userDao.addUser(user);
                    response.getWriter().write("{\"status\":\"success\"}");
                }
                
                else{
                    response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    response.getWriter().write("{\"status\":\"Not allowed, this username is exsists\"}");
                }

                
              
                response.setContentType("application/json");
                

            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
            } 
     
    }
    
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }
    
    @Override 
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest (request, response);
        
    }
}
