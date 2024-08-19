/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.data.model;

import com.univaq.procurement_portal_back_end.data.dao.ProposalDAO;
import com.univaq.procurement_portal_back_end.resources.DBConnectionManager;
import com.univaq.procurement_portal_back_end.resources.Features;
import com.univaq.procurement_portal_back_end.resources.Proposal;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
* @author BATOUL
 */
public class ProposalDAOImp implements ProposalDAO {
    
    private final DBConnectionManager dbManager;

    public ProposalDAOImp() {
        this.dbManager = new DBConnectionManager();
    }

 
    @Override
    public int insertProposal(Proposal proposal) {
         Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = dbManager.getConnection();

        String sql = "{CALL submit_proposal(?, ?, ?, ?, ?, ?, ?, ?)}";
        statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, proposal.getRequestId());
        statement.setInt(2, proposal.getTechnicianId());
        statement.setString(3, proposal.getProductName());
        statement.setString(4, proposal.getManufacturer());
        statement.setString(5, proposal.getProposalCode());
        statement.setBigDecimal(6, proposal.getPrice());
        statement.setString(7, proposal.getProposalURL());
        statement.setString(8, proposal.getNotes());

        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1); // Retrieve the auto-generated key from the DB
        } else {
            throw new SQLException("Creating proposal failed, no ID obtained.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return 0; // Return 0 or any other value that indicates an error
    } finally {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

    @Override
    public void insertProposalImage(int proposalId, InputStream image) {
        
        try {
            // Encrypt the password
         //   String encryptedPassword = EncryptionUtil.encrypt(user.getHashedPassword(), SECRET_KEY);

            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = dbManager.getConnection();

                String sql = "{CALL insert_poposal_images(?, ?)}";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, proposalId);
                statement.setBlob(2, image);


                statement.executeUpdate();
                


            }  finally {
                try {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }


    }

    @Override
    public Proposal getProposalByRequestId(int request_id) {
        Proposal proposal = new Proposal();
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL view_proposal(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, request_id);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    
                    proposal.setProposalId(rs.getInt("id"));
                    proposal.setRequestId(rs.getInt("request_id"));
                    proposal.setProductName(rs.getString("product_name"));
                    proposal.setManufacturer(rs.getString("manufacturer_name"));
                    proposal.setProposalCode(rs.getString("product_code"));
                    proposal.setPrice(rs.getBigDecimal("price"));
                    proposal.setProposalURL(rs.getString("product_URL"));
                    proposal.setNotes(rs.getString("notes"));
                    proposal.setStatus(rs.getString("proposal_status"));
                    proposal.setTechnicianId(rs.getInt("technician_id"));
                    proposal.setProposalCreatedDate(rs.getString("proposal_created_date"));
                    
                    
                    
     
                }
                


            }  finally {
                try {
                    if (statement != null) statement.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
        e.printStackTrace();
    }
        return proposal;
    }

    @Override
    public void approveProposal(int propsal_id) {
        Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = dbManager.getConnection();

        String sql = "{CALL approve_proposal(?)}";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, propsal_id);

        statement.executeUpdate();
        
    } catch (Exception e) {
        e.printStackTrace();
        
    } finally {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

    @Override
    public void rejectProposal(int proposal_id, String reason) {
        Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = dbManager.getConnection();

        String sql = "{CALL reject_proposal(?,?)}";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, proposal_id);
        statement.setString(2, reason);

        statement.executeUpdate();
        
    } catch (Exception e) {
        e.printStackTrace();
        
    } finally {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

    @Override
    public Proposal getRejectedProposal(int request_id) {   
        Proposal proposal = new Proposal();
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL view_proposal(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, request_id);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    
                    proposal.setProposalId(rs.getInt("id"));
                    proposal.setRequestId(rs.getInt("request_id"));
                    proposal.setProductName(rs.getString("product_name"));
                    proposal.setManufacturer(rs.getString("manufacturer_name"));
                    proposal.setProposalCode(rs.getString("product_code"));
                    proposal.setPrice(rs.getBigDecimal("price"));
                    proposal.setProposalURL(rs.getString("product_URL"));
                    proposal.setNotes(rs.getString("notes"));
                    proposal.setStatus(rs.getString("proposal_status"));
                    proposal.setTechnicianId(rs.getInt("technician_id"));
                    proposal.setProposalCreatedDate(rs.getString("proposal_created_date"));
                    proposal.setRejectionReason(rs.getString("rejection_reason"));
                    
                    
                    
     
                }
                


            }  finally {
                try {
                    if (statement != null) statement.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
        e.printStackTrace();
    }
        return proposal;
    }

    @Override
    public int getRequestIdByProposalId(int proposal_id) {
        
        int  requestId = 0 ;
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL get_requestId_by_proposalId(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, proposal_id);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    requestId = rs.getInt("proposal_id");
                    
                }
                


            }  finally {
                try {
                    if (statement != null) statement.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
        e.printStackTrace();
    }
        return requestId;
    }

   
    
}
