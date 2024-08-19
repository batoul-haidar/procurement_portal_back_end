/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

/**
 ** @author BATOUL
 */
public class Features {
    private int productId;
    private String name;
    private String options;
    
    public Features(){
    }
    
    public void setProductId(int ProductId){
        this.productId = ProductId;
    }
    
    public void setName (String Name){
        this.name = Name;
    }
    
    public void setOptions (String Options){
        this.options = Options;
    }
    
    public int getProductId(){
        return this.productId;
    }
    
       
    public String getName (){
        return this.name;
    }
    
    public String getOptions (){
        return this.options;
    }
}
