/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

/**
 ** @author BATOUL
 */
public class NewRequest {
    
    private int requestId, quantity;
    private String categoryName, productTypeName, productName, features, additionalRequirements, requestStatus, requestDat;
    
    public NewRequest (){
        
    }
    
    public void setRequestId(int RequestId){
        this.requestId=RequestId;
    }
    
    public void setQuantity(int Quantity){
        this.quantity=Quantity;
    }
    
    public void setCategoryName(String CategoryName){
        this.categoryName=CategoryName;
    }
    
    public void setProductTypeName(String ProductTypeName){
        this.productTypeName=ProductTypeName;
    }
    
    public void setProductName(String ProductName){
        this.productName=ProductName;
    }
    
    public void setFeatrues(String Features){
        this.features=Features;
    }
    
    
    
}


