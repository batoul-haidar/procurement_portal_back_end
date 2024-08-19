/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univaq.procurement_portal_back_end.data.dao;

import com.univaq.procurement_portal_back_end.resources.Proposal;
import java.io.InputStream;

/**
 *
* @author BATOUL
 */
public interface ProposalDAO {
    int insertProposal(Proposal proposal);
    void insertProposalImage(int proposalId, InputStream image);
    Proposal getProposalByRequestId(int request_id);
    void approveProposal(int propsal_id);
    void rejectProposal(int proposal_id, String reason);
    Proposal getRejectedProposal (int request_id);
    int getRequestIdByProposalId (int proposal_id);
}
