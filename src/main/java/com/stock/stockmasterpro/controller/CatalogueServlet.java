package com.stock.stockmasterpro.controller;

import com.stock.stockmasterpro.model.Produit;
import com.stock.stockmasterpro.service.ProduitService;

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
import java.util.List;

@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {

    private ProduitService produitService = new ProduitService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Produit> listeProduits = produitService.getAllProduits();
        request.setAttribute("listeProduits", listeProduits);



        Cookie[] cookies = request.getCookies();
        String lastVisit = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lastVisit".equals(cookie.getName())) {
                    lastVisit = cookie.getValue();
                }
            }
        }


        request.setAttribute("lastVisit", lastVisit);




        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);

        Cookie newCookie = new Cookie("lastVisit", now);
        newCookie.setMaxAge(60 * 60 * 24); // 24 heures
        response.addCookie(newCookie);




        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/vues/catalogue.jsp");

        dispatcher.forward(request, response);
    }
}
