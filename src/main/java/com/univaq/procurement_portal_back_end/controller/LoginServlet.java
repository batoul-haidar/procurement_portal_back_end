/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.univaq.procurement_portal_back_end.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univaq.procurement_portal_back_end.Utils.ServletHelpers;
import com.univaq.procurement_portal_back_end.Result.HTMLResult;
import com.univaq.procurement_portal_back_end.data.dao.UserDAO;
import com.univaq.procurement_portal_back_end.data.model.UserDAOImp;
import com.univaq.procurement_portal_back_end.resources.User;
import com.univaq.procurement_portal_back_end.security.SecurityHelpers;


import java.io.*;
import static java.lang.System.out;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
* @author BATOUL
 */
public class LoginServlet extends HttpServlet {


    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Welcome");
        result.appendToBody("<h1>Please Login</h1>");
        //
        if (request.getAttribute("https-redirect") != null) {
            result.appendToBody("<p>WARNING! you are not using a secured (https) connection! "
                    + "Please follow <a href=\"" + request.getAttribute("https-redirect") + "\">this url</a> to login securely!</p>");
        }
        //
        result.appendToBody("<form method=\"post\" action=\"login\">");
        result.appendToBody("<p>Username: <input name=\"u\" type=\"text\"/></p>");
        result.appendToBody("<p>Password: <input name=\"p\" type=\"password\"/><br/><small>Hint: try &quot;p&quot;</small></p>");
        if (request.getParameter("referrer") != null) {
            result.appendToBody("<input name=\"referrer\" type=\"hidden\" value=\"" + request.getParameter("referrer") + "\"/></p>");
        }
        result.appendToBody("<p><input value=\"login\" name=\"login\" type=\"submit\"/></p>");
        result.appendToBody("</form>");
        result.activate(request, response);
    }
    

    private void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException {
         BufferedReader reader = request.getReader();
         Gson gson = new Gson();
         JsonObject json = gson.fromJson(reader, JsonObject.class);
         
         String username = json.get("user_name").getAsString();
         String password = json.get("password").getAsString();
        /*
        String username = request.getParameter("user_name");
        String password = request.getParameter("password");*/
        //... VALIDAZIONE IDENTITA'...
        //... IDENTITY CHECKS ...
        System.out.println("Received login request for username: " + username);
        
        if (!username.isEmpty() && !password.isEmpty()) {
            UserDAO userDAO = new UserDAOImp();
            User user = userDAO.getUser(username);
           
        //    User u = User.forUsername(username);
            try {
                if (user != null && SecurityHelpers.checkPasswordHashPBKDF2(password, user.getHashedPassword())) {
// Retrieve user type
                String userType = user.getType();

// If the identity validation succeeds
    SecurityHelpers.createSession(request, username, user.getId(), userType);

    // Set the response status to 200 OK
    response.setStatus(HttpServletResponse.SC_OK);

    // Optionally, write a response message (e.g., a JSON object)
   // response.setContentType("application/json");
 //   response.setCharacterEncoding("UTF-8");
 //   response.getWriter().write("{\"message\": \"Login successful\"}");
    
  

                // Create JSON response
            //    Gson gson = new Gson();
                JsonObject jsonResponse = new JsonObject();
          //      jsonResponse.addProperty("status", "OK");
                jsonResponse.addProperty("message", "Login successful");
                jsonResponse.addProperty("user_type", userType);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(gson.toJson(jsonResponse));
    return;
} else {
    // If login fails, set the response status to 401 Unauthorized
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    // Optionally, write an error message
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write("{\"message\": \"Invalid username or password\"}");
}

                /*
                if (user != null && SecurityHelpers.checkPasswordHashPBKDF2(password, user.getHashedPassword())) {
                    //se la validazione ha successo
                    //if the identity validation succeeds
                    SecurityHelpers.createSession(request, username, user.getId());
                    //se è stato trasmesso un URL di origine, torniamo a quell'indirizzo
                    //if an origin URL has been transmitted, return to it
                    if (request.getParameter("referrer") != null) {
                        response.sendRedirect(request.getParameter("referrer"));
                    } else {
                        response.sendRedirect("homepage");
                    }
                    return;
                }*/
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //se la validazione fallisce...
        //if the validation fails...
      //  ServletHelpers.handleError("Login failed", request, response, getServletContext());
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            action_login(request, response); // batoul
            /*
            if (request.getParameter("login") != null) {
                action_login(request, response);
            } else {
                //tecnica base per forzare la ridirezione su HTTPS
                //andrebbe posta ad esempio in un filtro per poterla usare
                //su ogni richiesta senza replicare codice
                //basic technique to force redirection on HTTPS 
                //It should be placed, e.g., in a filter to easily use it 
                //on every request without replicating the code

                String https_redirect_url = SecurityHelpers.checkHttps(request);
                request.setAttribute("https-redirect", https_redirect_url);
                //non eseguiamo la ridirezione, altrimenti sui vostri server di prova non funzionerebbe
                //we do not redirect, otherwise it would not work on your test servers                
                //if (https_redirect_url != null) {
                //    response.sendRedirect(https_redirect_url);
                //} else {
                action_default(request, response);
                //}
            }*/
        } catch (IOException ex) {
            ServletHelpers.handleError(ex, request, response, getServletContext());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
