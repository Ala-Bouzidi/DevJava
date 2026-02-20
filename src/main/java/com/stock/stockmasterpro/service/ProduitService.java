package com.stock.stockmasterpro.service;

import com.stock.stockmasterpro.dao.ProduitDAO;
import com.stock.stockmasterpro.model.Produit;
import java.util.List;

public class ProduitService {

    private ProduitDAO produitDAO = new ProduitDAO();

    public ProduitService() {
    }

    public List<Produit> getAllProduits() {
        return produitDAO.findAll();
    }
}