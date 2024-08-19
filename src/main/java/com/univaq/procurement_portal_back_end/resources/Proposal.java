/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

import java.math.BigDecimal;

/**
 ** @author BATOUL
 */
public class Proposal {

    
    
    public Proposal (){
        
    }

    public Proposal(int requestId, String productName, String manufacturer, String proposalCode, BigDecimal price, String proposalURL, String notes, int technicianId) {
        this.requestId = requestId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.proposalCode = proposalCode;
        this.price = price;
        this.proposalURL = proposalURL;
        this.notes = notes;
        this.technicianId = technicianId;
    }
    
    
    public Proposal(int proposalId, int requestId, String productName, String manufacturer, String productCode, BigDecimal price, String proposalURL,  String notes, String proposalStatus, int technicianId, String createdDate) {
        this.proposalId = proposalId;
        this.requestId = requestId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.proposalCode = productCode;
        this.price = price;
        this.proposalURL = proposalURL;
        this.notes = notes;
        this.status= proposalStatus;
        this.proposalCreatedDate = createdDate;
        this.technicianId = technicianId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProposalCreatedDate() {
        return proposalCreatedDate;
    }

    public void setProposalCreatedDate(String proposalCreatedDate) {
        this.proposalCreatedDate = proposalCreatedDate;
    }

    public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProposalCode() {
        return proposalCode;
    }

    public void setProposalCode(String proposalCode) {
        this.proposalCode = proposalCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal  price) {
        this.price = price;
    }

    public String getProposalURL() {
        return proposalURL;
    }

    public void setProposalURL(String proposalURL) {
        this.proposalURL = proposalURL;
    }
    
    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
    
    
    
    private int proposalId;
    private int requestId;
    private String productName;
    private String manufacturer;
    private String proposalCode;
    private BigDecimal price;
    private String proposalURL;
    private String status;
    private String technicianName;
    private String notes;
    private int technicianId;
    private String proposalCreatedDate;
    private String rejectionReason;
}
