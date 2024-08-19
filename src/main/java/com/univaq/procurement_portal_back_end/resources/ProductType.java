/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

/**
 *
 * @author Batoul
 */
public class ProductType {
    private int id;
    private int categoryId;
    private String name;

    // Constructor
    public ProductType() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Optional: Override toString for debugging
    @Override
    public String toString() {
        return "ProductType{" +
               "id=" + id +
               ", categoryId=" + categoryId +
               ", name='" + name + '\'' +
               '}';
    }
}
