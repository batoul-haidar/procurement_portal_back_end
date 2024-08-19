/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.univaq.procurement_portal_back_end.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univaq.procurement_portal_back_end.data.dao.PurchaserReqDAO;
import com.univaq.procurement_portal_back_end.data.model.PurchaserReqDAOImp;
import com.univaq.procurement_portal_back_end.resources.NotificationService;
import com.univaq.procurement_portal_back_end.resources.PurchaserRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
* @author BATOUL
 */
public class ApproveRequestByTechServlet extends HttpServlet {
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
        
        HttpSession session = request.getSession(false);
        System.out.println("Processing request. Session ID: " + (session != null ? session.getId() : "null"));
        if (session != null) {
            System.out.println("UserID in session: " + session.getAttribute("userid"));
        }
        
       // HttpSession s = r.getSession(false);
        int requestId = json.get("request_id").getAsInt();
        
        
        int userId=(Integer) session.getAttribute("userid");

            try {    
                
                
                PurchaserReqDAO reqDao = new PurchaserReqDAOImp();
                reqDao.approveRequestByTechnician(requestId, userId);
               
              //  AddUserDAO addUser = new AddUserDAO();
              //  addUser.setUser(user_type, user_full_name, user_name, password, user_departement, other_departement);

                response.setStatus(HttpServletResponse.SC_OK);
                
                int purchaserId = reqDao.getPurchaserByRequestID(requestId);
                // Create a notification for all technicians
        notificationService.addNotification("Request approved and now in progress", "my_requests.html", purchaserId, null);


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
