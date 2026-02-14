package com.stock.stockmasterpro.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        // Pages publiques (sans authentification)
        boolean isLoginPage = uri.endsWith("login.jsp");
        boolean isLoginServlet = uri.endsWith("/connexion");

        HttpSession session = req.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn || isLoginPage || isLoginServlet) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(contextPath + "/login.jsp");
        }
    }
}