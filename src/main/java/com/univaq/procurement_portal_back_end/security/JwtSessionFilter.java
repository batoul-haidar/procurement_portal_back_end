/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.security;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

/**
 ** @author BATOUL
 */
public class JwtSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (validateSession(httpRequest)) {
            chain.doFilter(request, response); // Valid token, proceed with the request
        } else {
            // Invalid token or no token, block the request
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing token");
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }

    private boolean validateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String jwt = (String) session.getAttribute("session-id");
            return jwtUtil.validateToken(jwt);
        }
        return false;
    }
}
