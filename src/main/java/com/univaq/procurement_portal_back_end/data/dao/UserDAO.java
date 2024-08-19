/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univaq.procurement_portal_back_end.data.dao;

import com.univaq.procurement_portal_back_end.resources.User;
import java.util.List;



/**
 ** @author BATOUL
 */
public interface UserDAO {
    void addUser(User user);
    void updateUser(User user);
    void updatePassword(int userId, String password);
    void deleteUser(int userId);
    User getUser(String userName);
    List<User> getAllUsers();
    boolean checkUserName(String username);
}