/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

import com.univaq.procurement_portal_back_end.security.SecurityHelpers;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** @author BATOUL
 */
public class User {
    private int id;
    private String fullName;
    private String type;
    private String userName;
    private String hashedPassword;
    private String userDepartement;
    private String otherDepartement;
    private String addedDate;

    
    public User (){
        
    }
    
    public User (int userId, String userType,String fullName, String userDepartement, String otherDepartement){
        this.id=userId;
        this.type=userType;
        this.fullName=fullName;
        this.userDepartement=userDepartement;
        this.otherDepartement=otherDepartement;
    }
    
    public User ( String fullName,String type,String userName,String hashedPassword,String userDepartement,String otherDepartement){
        this.fullName = fullName;
        this.type = type;
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.userDepartement = userDepartement;
        this.otherDepartement = otherDepartement;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHashedPassword(String HashedPassword) {
        this.hashedPassword = HashedPassword;
    }

    public void setUserDepartement(String userDepartement) {
        this.userDepartement = userDepartement;
    }

    public void setOtherDepartement(String otherDepartement) {
        this.otherDepartement = otherDepartement;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getType() {
        return type;
    }

    public String getUserName() {
        return userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getUserDepartement() {
        return userDepartement;
    }

    public String getOtherDepartement() {
        return otherDepartement;
    }

    public String getAddedDate() {
        return addedDate;
    }
    
    
    //create a fake user for testing purposes
    public static User forUsername(String username) {
        User u = new User();
        u.setId(1);
        u.setUserName(username);
        try {
            //solo per test: la password hashed deve essere letta dal database degli utenti!            
            //test-only: hashed password must be read from the user database!
            u.setHashedPassword(SecurityHelpers.getPasswordHashPBKDF2("p"));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    
    
    
}
