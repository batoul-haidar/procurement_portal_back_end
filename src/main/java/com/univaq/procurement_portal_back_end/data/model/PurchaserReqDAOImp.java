/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.data.model;

import com.univaq.procurement_portal_back_end.resources.Category;
import com.univaq.procurement_portal_back_end.resources.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.univaq.procurement_portal_back_end.data.dao.PurchaserReqDAO;
import com.univaq.procurement_portal_back_end.resources.Features;
import com.univaq.procurement_portal_back_end.resources.Product;
import com.univaq.procurement_portal_back_end.resources.ProductType;
import com.univaq.procurement_portal_back_end.resources.PurchaserRequest;

/**
 *
* @author BATOUL
 */
public class PurchaserReqDAOImp implements PurchaserReqDAO {

    
    private final DBConnectionManager dbManager;


    public PurchaserReqDAOImp() {
        this.dbManager = new DBConnectionManager();
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM categories";

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public List<ProductType> getProductTypesByCategory(int categoryId) {
        List<ProductType> productTypes = new ArrayList<>();
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "SELECT id, category_id, name FROM product_types WHERE category_id = ?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, categoryId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    ProductType productType = new ProductType();
                    productType.setId(rs.getInt("id"));
                    productType.setCategoryId(rs.getInt("category_id"));
                    productType.setName(rs.getString("name"));
                    productTypes.add(productType);
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
        return productTypes;
        

    }

    @Override
    public List<Product> getProductsByType(int productTypeId) {
        
        List<Product> products = new ArrayList<>();
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "SELECT id, product_type_id, name FROM products WHERE product_type_id = ?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, productTypeId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductTypeId(rs.getInt("product_type_id"));
                product.setName(rs.getString("name"));
                products.add(product);
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
        return products;

    }

    @Override
    public List<Features> getFeaturesByProduct(int productId) {
        List<Features> features = new ArrayList<>();
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "SELECT product_id, feature_name, options  FROM features WHERE product_id = ?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, productId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    Features feature = new Features();
                    feature.setProductId(rs.getInt("product_id"));
                    feature.setName(rs.getString("feature_name"));
                    feature.setOptions(rs.getString("options"));
                    features.add(feature);
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
        return features;
    }

    @Override
    public void makeNewRequest(PurchaserRequest request) {
        try {
            // Encrypt the password
         //   String encryptedPassword = EncryptionUtil.encrypt(user.getHashedPassword(), SECRET_KEY);

            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = dbManager.getConnection();

                String sql = "{CALL submit_request(?, ?, ?, ?, ?, ?, ?)}";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, request.getPurchaserId());
                statement.setInt(2, request.getCategoryId());
                statement.setInt(3, request.getProductTypeId());
                statement.setInt(4, request.getProductId());
             //   statement.setBytes(4, encryptedPassword.getBytes());  // Store the encrypted password as bytes
                statement.setString(5, request.getFeatures());
                statement.setString(6, request.getAddRequirements());
                statement.setInt(7, request.getQuantity());

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
    public List<PurchaserRequest> ListNewRequests() {
        List<PurchaserRequest> reqs = new ArrayList<>();
        String sql = "CALL list_new_requests()";
        
        try(Connection conn = dbManager.getConnection();
                PreparedStatement stmt = conn.prepareCall(sql);
                ResultSet rs = stmt.executeQuery()){
            
            while (rs.next()){
                PurchaserRequest req = new PurchaserRequest(
                rs.getInt("request_id"),
                rs.getString("category"),
                rs.getString("product_type"),
                rs.getString("product"),
                rs.getString("features"),
                rs.getString("additional_requirements"),
                rs.getInt("quantity"),
                rs.getString("request_status"),
                        rs.getString("purchaser_name"),
                rs.getDate("request_date").toString());
                
                reqs.add(req);
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return reqs;
    }

    @Override
    public List<PurchaserRequest> ListTechnicianRequests(int techId) {
        List<PurchaserRequest> reqs = new ArrayList<>();
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL list_requests_by_technician(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, techId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                PurchaserRequest req = new PurchaserRequest(
                rs.getInt("request_id"),
                rs.getString("category"),
                rs.getString("product_type"),
                rs.getString("product"),
                rs.getString("features"),
                rs.getString("additional_requirements"),
                rs.getInt("quantity"),
                rs.getString("request_status"),
                        rs.getString("purchaser_name"),
                        rs.getString("technician_name"),
                rs.getDate("request_date").toString());
                
                reqs.add(req);
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
        return reqs;

    }

    @Override
    public List<PurchaserRequest> ListRequestsByPurchaser(int purchaserId) {
        List<PurchaserRequest> reqs = new ArrayList<>();
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL list_requests_by_purchaser(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, purchaserId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                PurchaserRequest req = new PurchaserRequest(
                rs.getInt("request_id"),
                rs.getString("category"),
                rs.getString("product_type"),
                rs.getString("product"),
                rs.getString("features"),
                rs.getString("additional_requirements"),
                rs.getInt("quantity"),
                rs.getString("request_status"),
                        rs.getString("purchaser_name"),
                rs.getDate("request_date").toString());
                
                reqs.add(req);
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
        return reqs;


    
    }

    @Override
    public void approveRequestByTechnician(int requestId, int technicianId) {
        try {
            // Encrypt the password
         //   String encryptedPassword = EncryptionUtil.encrypt(user.getHashedPassword(), SECRET_KEY);

            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = dbManager.getConnection();

                String sql = "{CALL approveRequestByTechnician(?, ?)}";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, requestId);
                statement.setInt(2, technicianId);

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
    public void closeRequest(int requestId) {
        
        Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = dbManager.getConnection();

        String sql = "{CALL close_request(?)}";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, requestId);

        statement.executeUpdate();
        
    } catch (Exception e) {
        e.printStackTrace();
        
    } finally {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

    @Override
    public List<PurchaserRequest> ListHistoryByPurchaser(int purchaserId) {
        List<PurchaserRequest> reqs = new ArrayList<>();
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL list_history_by_purchaser(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, purchaserId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                PurchaserRequest req = new PurchaserRequest(
                rs.getInt("request_id"),
                rs.getString("category"),
                rs.getString("product_type"),
                rs.getString("product"),
                rs.getString("features"),
                rs.getString("additional_requirements"),
                rs.getInt("quantity"),
                rs.getString("request_status"),
                        rs.getString("purchaser_name"),
                rs.getDate("request_date").toString());
                
                reqs.add(req);
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
        return reqs;


    }

    @Override
    public List<PurchaserRequest> ListHistoryByTechnician(int techId) {
        
        List<PurchaserRequest> reqs = new ArrayList<>();
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL list_history_by_technician(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, techId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                PurchaserRequest req = new PurchaserRequest(
                rs.getInt("request_id"),
                rs.getString("category"),
                rs.getString("product_type"),
                rs.getString("product"),
                rs.getString("features"),
                rs.getString("additional_requirements"),
                rs.getInt("quantity"),
                rs.getString("request_status"),
                        rs.getString("purchaser_name"),
                        rs.getString("technician_name"),
                rs.getDate("request_date").toString());
                
                reqs.add(req);
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
        return reqs;

    }

    @Override
    public int getPurchaserByRequestID(int requestId) {
        int  purchaserId = 0 ;
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL getPurchaserByRequestId(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, requestId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    purchaserId = rs.getInt("purchaser_id");
                    
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
        return purchaserId;
        
    }

    @Override
    public int getTechnicianByRequestID(int requestId) {
        int  technicianId = 0 ;
        
        try{
            Connection conn = null;
            PreparedStatement statement = null;
            
            try {
                conn = dbManager.getConnection();

                String sql = "CALL getTechnicianByRequestId(?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, requestId);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()){
                    technicianId = rs.getInt("purchaser_id");
                    
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
        return technicianId;
    }
}
