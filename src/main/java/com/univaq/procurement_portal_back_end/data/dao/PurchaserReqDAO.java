/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univaq.procurement_portal_back_end.data.dao;

import com.univaq.procurement_portal_back_end.resources.Category;
import com.univaq.procurement_portal_back_end.resources.Features;
import com.univaq.procurement_portal_back_end.resources.Product;
import com.univaq.procurement_portal_back_end.resources.ProductType;
import com.univaq.procurement_portal_back_end.resources.PurchaserRequest;
import java.util.List;

/**
 *
* @author BATOUL
 */
public interface PurchaserReqDAO {
    
   // Category getCategory(int id); 
    List<Category> getAllCategories(); 
    List<ProductType> getProductTypesByCategory (int categoryId);
    List<Product> getProductsByType (int productTypeId);
    List<Features> getFeaturesByProduct (int productId);
    void makeNewRequest (PurchaserRequest request);
    List<PurchaserRequest> ListNewRequests();
    List<PurchaserRequest> ListRequestsByPurchaser(int purchaserId);
    List<PurchaserRequest> ListTechnicianRequests(int techId);
    void approveRequestByTechnician (int requestId, int technicianId);
    void closeRequest ( int requestId);
    

    List<PurchaserRequest> ListHistoryByPurchaser(int purchaserId);
    List<PurchaserRequest> ListHistoryByTechnician(int techId);
    
    int getPurchaserByRequestID (int requestId);
    int getTechnicianByRequestID (int requestId);

}
