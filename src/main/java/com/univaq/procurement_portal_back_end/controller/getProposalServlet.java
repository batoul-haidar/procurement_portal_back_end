/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.univaq.procurement_portal_back_end.controller;

import com.google.gson.Gson;
import com.univaq.procurement_portal_back_end.data.dao.ProposalDAO;
import com.univaq.procurement_portal_back_end.data.dao.PurchaserReqDAO;
import com.univaq.procurement_portal_back_end.data.model.ProposalDAOImp;
import com.univaq.procurement_portal_back_end.data.model.PurchaserReqDAOImp;
import com.univaq.procurement_portal_back_end.resources.Features;
import com.univaq.procurement_portal_back_end.resources.Proposal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 ** @author BATOUL
 */

public class getProposalServlet extends HttpServlet {

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
        
        String request_id_param = request.getParameter("request_id");
        PrintWriter out = response.getWriter();
        
        try {
            int requestId = Integer.parseInt(request_id_param);
            ProposalDAO proposalDao = new ProposalDAOImp();
            Proposal proposal = proposalDao.getProposalByRequestId(requestId);
            
            Gson gson = new Gson();
            String jsonWrite = gson.toJson(proposal);
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(jsonWrite);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        } finally {
            out.flush();
            out.close();
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
