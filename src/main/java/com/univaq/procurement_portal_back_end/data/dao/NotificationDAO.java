/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univaq.procurement_portal_back_end.data.dao;

import com.univaq.procurement_portal_back_end.resources.Notification;
import java.sql.SQLException;
import java.util.List;

/**
 *
* @author BATOUL
 */
public interface NotificationDAO {
    void addNotification(Notification notification);
    List<Notification> getNotificationsForUser(int userId, String userType);
    void markNotificationsAsRead(int userId, String userType);
}
