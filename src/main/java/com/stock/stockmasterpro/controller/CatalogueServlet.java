package com.stock.stockmasterpro.controller;

import com.stock.stockmasterpro.model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // -------------------------------
        // 1️⃣ PRODUCTS
        // -------------------------------
        List<Produit> listeProduits = new ArrayList<>();
        listeProduits.add(new Produit(1, "Clavier", 25.0));
        listeProduits.add(new Produit(2, "Souris", 15.5));
        listeProduits.add(new Produit(3, "Écran", 199.99));
        listeProduits.add(new Produit(4, "PC Portable", 899.0));

        request.setAttribute("listeProduits", listeProduits);

        // -------------------------------
        // 2️⃣ COOKIE MANAGEMENT
        // -------------------------------

        String lastVisit = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("lastVisit")) {
                    lastVisit = c.getValue();
                }
            }
        }

        request.setAttribute("lastVisit", lastVisit);

        // ⚠ SAFE FORMAT WITHOUT SPACES
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

        String currentTime =
                LocalDateTime.now().format(formatter);

        Cookie newCookie =
                new Cookie("lastVisit", currentTime);

        newCookie.setMaxAge(60 * 60 * 24); // 24h
        newCookie.setPath(request.getContextPath());

        response.addCookie(newCookie);

        // -------------------------------
        // 3️⃣ FORWARD TO JSP
        // -------------------------------

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/vues/catalogue.jsp");

        dispatcher.forward(request, response);
    }
}
