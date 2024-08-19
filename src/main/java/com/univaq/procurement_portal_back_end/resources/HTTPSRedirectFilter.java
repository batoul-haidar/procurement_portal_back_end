/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univaq.procurement_portal_back_end.resources;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author BATOUL
 */
public class HTTPSRedirectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("HTTPSRedirectFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("HTTPSRedirectFilter doFilter called for: " + req.getRequestURI());

        if (req.getScheme().equals("http")) {
            String url = "https://" + req.getServerName() + ":8443" + req.getRequestURI();
            if (req.getQueryString() != null) {
                url += "?" + req.getQueryString();
            }
            System.out.println("Redirecting to: " + url);
            res.sendRedirect(url);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("HTTPSRedirectFilter destroyed");
    }
}
