/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

/**
 ** @author BATOUL
 */
public class Product {
    
    private int id;
    private String name;
    private int productTypeId;
    
    public Product(){
    }
    
    public void setId(int Id){
        this.id = Id;
    }
    
    public void setProductTypeId (int ProductTypeId){
        this.productTypeId = ProductTypeId;
    }
    
    public void setName (String Name){
        this.name = Name;
    }
    
    public int getId(){
        return this.id;
    }
    
    public int getProductTypeId(){
        return this.productTypeId;
    }
    
    public String getName (){
        return this.name;
    }
}
