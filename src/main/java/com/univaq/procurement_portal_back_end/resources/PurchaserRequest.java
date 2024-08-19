/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

/**
 ** @author BATOUL
 */
public class PurchaserRequest {

   
    

    public PurchaserRequest() {
        
    }
    
    

    public PurchaserRequest(int purchaserId, int categoryId, int productTypeId, int productId, String features, String addRequirements, int quantity) {
        this.purchaserId = purchaserId;
        this.categoryId = categoryId;
        this.productTypeId = productTypeId;
        this.productId = productId;
        this.features = features;
        this.addRequirements = addRequirements;
        this.quantity = quantity;
    }
    
    public PurchaserRequest(int requestId, String categoryName, String productTypeName, String productName, String features, String addRequirements, int quantity,  String status, String purchaserName, String added_date) {
        this.features = features;
        this.addRequirements = addRequirements;
        this.quantity = quantity;
        this.requestId = requestId;
        this.status = status;
        this.added_date = added_date;
        this.categoryName = categoryName;
        this.productTypeName = productTypeName;
        this.productName = productName;
        this.purchaserName = purchaserName;

    }

    public PurchaserRequest(int requestId, String categoryName, String productTypeName, String productName, String features, String addRequirements, int quantity,  String status, String purchaserName,String technicianName, String added_date) {
        this.features = features;
        this.addRequirements = addRequirements;
        this.quantity = quantity;
        this.requestId = requestId;
        this.status = status;
        this.added_date = added_date;
        this.categoryName = categoryName;
        this.productTypeName = productTypeName;
        this.productName = productName;
        this.purchaserName = purchaserName;
        this.technicianName = technicianName;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getAddRequirements() {
        return addRequirements;
    }

    public void setAddRequirements(String addRequirements) {
        this.addRequirements = addRequirements;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdded_date() {
        return added_date;
    }

    public void setAdded_date(String added_date) {
        this.added_date = added_date;
    }
    
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    
    public int getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(int purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    
     public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    
    private int productTypeId;
    private int productId;
    private String features;
    private String addRequirements;
    private int quantity;
    private int requestId;
    private int categoryId;
    private String status;
    private String added_date;
    
    private String categoryName;
    private String productTypeName;
    private String productName;
    
    private int purchaserId;
    private String purchaserName;
    
    private int technicianId;
    private String technicianName;
}
