/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.univaq.procurement_portal_back_end.controller;

import com.google.gson.Gson;
import com.univaq.procurement_portal_back_end.data.dao.PurchaserReqDAO;
import com.univaq.procurement_portal_back_end.data.model.PurchaserReqDAOImp;
import com.univaq.procurement_portal_back_end.resources.PurchaserRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 ** @author BATOUL
 */
public class ListRequestsByPurchaser extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        PurchaserReqDAO reqDAO = new PurchaserReqDAOImp(); 
        
        HttpSession session = request.getSession(false);
        System.out.println("Processing request. Session ID: " + (session != null ? session.getId() : "null"));
        if (session != null) {
            System.out.println("UserID in session: " + session.getAttribute("userid"));
        }
        
        int userId=(Integer) session.getAttribute("userid");
        
        try{
            List<PurchaserRequest> reqs = reqDAO.ListRequestsByPurchaser(userId);
            
            // Transform the list of PurchaserRequest objects to a list of maps
        List<Map<String, Object>> filteredReqs = new ArrayList<>();
        for (PurchaserRequest req : reqs) {
            Map<String, Object> filteredReq = new LinkedHashMap <>();
            filteredReq.put("requestId", req.getRequestId());
            filteredReq.put("category", req.getCategoryName());
            filteredReq.put("productType", req.getProductTypeName());
            filteredReq.put("product", req.getProductName());
            filteredReq.put("features", req.getFeatures());
            filteredReq.put("additionalRequirements", req.getAddRequirements());
            filteredReq.put("quantity", req.getQuantity());
            filteredReq.put("requestStatus", req.getStatus());
            filteredReq.put("purchaserName", req.getPurchaserName());
            filteredReq.put("requestDate", req.getAdded_date());
            filteredReqs.add(filteredReq);
        }
        
        
            Gson gson = new Gson();
            String json = gson.toJson(filteredReqs);
            out.print(json);
            out.flush();
        } catch(Exception e){
            e.printStackTrace();
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
