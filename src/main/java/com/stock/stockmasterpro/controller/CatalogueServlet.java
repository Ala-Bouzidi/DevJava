package com.stock.stockmasterpro.controller;

import com.stock.stockmasterpro.model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Produit> listeProduits = new ArrayList<>();
        listeProduits.add(new Produit(1, "Clavier", 25.0));
        listeProduits.add(new Produit(2, "Souris", 15.5));
        listeProduits.add(new Produit(3, "Écran", 199.99));
        listeProduits.add(new Produit(4, "PC Portable", 899.0));

        request.setAttribute("listeProduits", listeProduits);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vues/catalogue.jsp");
        dispatcher.forward(request, response);
    }
}