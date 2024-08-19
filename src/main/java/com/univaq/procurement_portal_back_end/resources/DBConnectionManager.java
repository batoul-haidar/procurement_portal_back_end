/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;




/**
 ** @author BATOUL
 */

public class DBConnectionManager {
    
//    private static final Logger LOGGER = Logger.getLogger(DBConnectionManager.class.getName());

  //  @Resource(name = "jdbc/procurement_porat")
    private DataSource dataSource;
    
    public DBConnectionManager() {
        
     //   LOGGER.info("DBConnectionManager created: " + this);
        
        System.out.println("DBConnectionManager created: " + this);
        try {
            // Get a reference to the naming context
            InitialContext ctx = new InitialContext();
            // And from it a DataSource reference
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/procurement_porat");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to lookup DataSource", e);
        }
    }
    
    /*
    @Resource(name = "jdbc/procurement_porat")
    private DataSource dataSource;*/
    
    

    public Connection getConnection() throws SQLException {
        System.out.println("Getting connection from DataSource: " + dataSource);
        return dataSource.getConnection();
    }
    
    
    

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
