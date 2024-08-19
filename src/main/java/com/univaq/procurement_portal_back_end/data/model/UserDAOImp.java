/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.data.model;

import com.univaq.procurement_portal_back_end.resources.DBConnectionManager;
import com.univaq.procurement_portal_back_end.resources.EncryptionUtil;
import com.univaq.procurement_portal_back_end.resources.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.crypto.SecretKey;
import com.univaq.procurement_portal_back_end.data.dao.UserDAO;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 ** @author BATOUL
 */
public class UserDAOImp implements UserDAO {

    private final DBConnectionManager dbManager;
    
  //  private static final SecretKey SECRET_KEY;
   

    static {
        try {
            // Generate a secret key. In a real-world scenario, you should securely store and manage this key.
       //     SECRET_KEY = EncryptionUtil.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate secret key", e);
        }
    }
    public UserDAOImp() {
        this.dbManager = new DBConnectionManager();
    }

    
    
    @Override
    public void addUser(User user) {
        try {
            // Encrypt the password
         //   String encryptedPassword = EncryptionUtil.encrypt(user.getHashedPassword(), SECRET_KEY);

            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = dbManager.getConnection();

                String sql = "{CALL add_user(?, ?, ?, ?, ?, ?)}";
                statement = connection.prepareStatement(sql);
                statement.setString(1, user.getType());
                statement.setString(2, user.getFullName());
                statement.setString(3, user.getUserName());
             //   statement.setBytes(4, encryptedPassword.getBytes());  // Store the encrypted password as bytes
                statement.setString(4, user.getHashedPassword());
                statement.setString(5, user.getUserDepartement());
                statement.setString(6, user.getOtherDepartement());

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
    public void updateUser(User user) {
        try {
            // Encrypt the password
         //   String encryptedPassword = EncryptionUtil.encrypt(user.getHashedPassword(), SECRET_KEY);

            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = dbManager.getConnection();

                String sql = "{CALL update_user_info(?, ?, ?, ?, ?)}";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, user.getId());
                statement.setString(2, user.getType());
                statement.setString(3, user.getFullName());
                statement.setString(4, user.getUserDepartement());
                statement.setString(5, user.getOtherDepartement());

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
    public void deleteUser(int userId) {
        try {
            // Encrypt the password
         //   String encryptedPassword = EncryptionUtil.encrypt(user.getHashedPassword(), SECRET_KEY);

            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = dbManager.getConnection();

                String sql = "{CALL delete_user(?)}";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, userId);

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
    public User getUser(String userName) {
        User u = new User();
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "SELECT user_id, user_name, hashed_password, user_full_name, user_type, user_departement, other_departement, added_date from users where user_name = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, userName);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    u.setId(rs.getInt("user_id"));
                    u.setUserName(rs.getString("user_name"));
                    u.setFullName(rs.getString("user_full_name"));
                    u.setHashedPassword(rs.getString("hashed_password"));
                    u.setType(rs.getString("user_type"));
                    u.setUserDepartement(rs.getString("user_departement"));
                    u.setOtherDepartement(rs.getString("other_departement"));
                    u.setAddedDate(rs.getDate("added_date").toString());
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
        
        
        return u;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, user_type, user_full_name, user_name, user_departement, other_departement, added_date from users";
        
        try(Connection conn = dbManager.getConnection();
                PreparedStatement stmt = conn.prepareCall(sql);
                ResultSet rs = stmt.executeQuery()){
            
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFullName(rs.getString("user_full_name"));
                user.setUserName(rs.getString("user_name"));
              //  user.setHashedPassword(rs.getString("hashed_password"));
                user.setType(rs.getString("user_type"));
                user.setUserDepartement(rs.getString("user_departement"));
                user.setOtherDepartement(rs.getString("other_departement"));
                user.setAddedDate(rs.getDate("added_date").toString());
                users.add(user);
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean checkUserName(String username) {
        boolean isUserNameExist=false;
        
        try{ 
            Connection conn = null;
            PreparedStatement statement = null;
            try{
                conn = dbManager.getConnection();
                
                String sql = "{CALL check_username(?)}";
                statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                ResultSet rs = statement.executeQuery();
                while (rs.next()){
                    int count = rs.getInt("user_count");
                    isUserNameExist = count>0;
                }    
                
            } finally{
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return isUserNameExist;
    }

    @Override
    public void updatePassword(int userId, String password) {
        try {


            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = dbManager.getConnection();

                String sql = "{CALL update_password(?, ?)}";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.setString(2, password);


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
   
}
