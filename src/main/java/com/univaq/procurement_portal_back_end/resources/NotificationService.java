/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

import com.univaq.procurement_portal_back_end.data.dao.NotificationDAO;
import com.univaq.procurement_portal_back_end.data.model.NotificationDAOImpl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
* @author BATOUL
 */
public class NotificationService {
      private NotificationDAO notificationDAO = new NotificationDAOImpl();

    public void addNotification(String message, String link, Integer userId, String userType) {
        Notification notification = new Notification(message, link, userId, userType, false, new Timestamp(System.currentTimeMillis()));
        notificationDAO.addNotification(notification);
    }

    public List<Notification> getNotificationsForUser(int userId, String userType) {
        return notificationDAO.getNotificationsForUser(userId, userType);
    }

    public void markNotificationsAsRead(int userId, String userType) {
        notificationDAO.markNotificationsAsRead(userId, userType);
    }
}
