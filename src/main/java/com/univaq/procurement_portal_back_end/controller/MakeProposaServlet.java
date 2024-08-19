/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.univaq.procurement_portal_back_end.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.univaq.procurement_portal_back_end.data.dao.ProposalDAO;
import com.univaq.procurement_portal_back_end.data.dao.PurchaserReqDAO;
import com.univaq.procurement_portal_back_end.data.model.ProposalDAOImp;
import com.univaq.procurement_portal_back_end.data.model.PurchaserReqDAOImp;
import com.univaq.procurement_portal_back_end.resources.NotificationService;
import com.univaq.procurement_portal_back_end.resources.Proposal;
import com.univaq.procurement_portal_back_end.resources.PurchaserRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
* @author BATOUL
 */
public class MakeProposaServlet extends HttpServlet {
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
        String productName = json.get("product_name").getAsString();
        String manufacturer = json.get("manufacturer").getAsString();
        String proposalCode = json.get("proposal_code").getAsString();
        BigDecimal price = json.get("price").getAsBigDecimal();
        String proposalURL = json.get("proposal_url").getAsString();
        String notes = json.get("notes").getAsString();
        

   
        int userId=(Integer) session.getAttribute("userid");
        int proposalId = 0;

            try {               

                Proposal proposal = new Proposal (requestId, productName, manufacturer, proposalCode, price, proposalURL, notes, userId);
                ProposalDAO proposalDao = new ProposalDAOImp();
                proposalId = proposalDao.insertProposal(proposal);
                
              //  AddUserDAO addUser = new AddUserDAO();
              //  addUser.setUser(user_type, user_full_name, user_name, password, user_departement, other_departement);




            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"status\":\"success\", \"proposalId\":" + proposalId + "}");
             
            PurchaserReqDAO reqDao = new PurchaserReqDAOImp();
             int purchaserId = reqDao.getPurchaserByRequestID(requestId);
                // Create a notification
        notificationService.addNotification("There is a new proposal", "my_requests.html", purchaserId, null);
   
                
            /*    
                 // Insert associated images
            for (Part part : request.getParts()) {
                if (part.getName().equals("proposal_images") && part.getSize() > 0) {
                    InputStream inputStream = part.getInputStream();
                    proposalDao.insertProposalImage(proposalId, inputStream);
                }
            }*/
            
        //    response.setStatus(HttpServletResponse.SC_OK);
        //    response.getWriter().write("{\"status\":\"success\"}");
                
                  
              
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
