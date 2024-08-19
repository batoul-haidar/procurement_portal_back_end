/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.data.model;

import com.univaq.procurement_portal_back_end.data.dao.NotificationDAO;
import com.univaq.procurement_portal_back_end.resources.DBConnectionManager;
import com.univaq.procurement_portal_back_end.resources.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
* @author BATOUL
 */
public class NotificationDAOImpl implements NotificationDAO {
    
     private final DBConnectionManager dbManager;

    public NotificationDAOImpl() {
        this.dbManager = new DBConnectionManager();
    }

    @Override
    public void addNotification(Notification notification) {
      //  Connection connection = null;
        String sql = "INSERT INTO notifications (message, link, user_id, user_type, is_read) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, notification.getMessage());
            pstmt.setString(2, notification.getLink());
            if (notification.getUserId() != null) {
                pstmt.setInt(3, notification.getUserId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setString(4, notification.getUserType());
            pstmt.setBoolean(5, notification.isRead());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notification> getNotificationsForUser(int userId, String userType) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications WHERE (user_id = ? OR user_type = ?) AND is_read = false ORDER BY created_at DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, userType);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification(
                        rs.getString("message"),
                        rs.getString("link"),
                        rs.getInt("user_id"),
                        rs.getString("user_type"),
                        rs.getBoolean("is_read"),
                        rs.getTimestamp("created_at")
                );
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public void markNotificationsAsRead(int userId, String userType) { 
        String sql = "UPDATE notifications SET is_read = true WHERE user_id = ? OR user_type = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, userType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

  
    
}
