package com.stock.stockmasterpro.controller;

import com.stock.stockmasterpro.dao.ProduitDAO;
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


        ProduitDAO dao = new ProduitDAO();
        List<Produit> listeProduits = dao.findAll();

        request.setAttribute("listeProduits", listeProduits);



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


        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

        String currentTime =
                LocalDateTime.now().format(formatter);

        Cookie newCookie =
                new Cookie("lastVisit", currentTime);

        newCookie.setMaxAge(60 * 60 * 24); // 24h
        newCookie.setPath(request.getContextPath());

        response.addCookie(newCookie);



        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/vues/catalogue.jsp");

        dispatcher.forward(request, response);
    }
}
