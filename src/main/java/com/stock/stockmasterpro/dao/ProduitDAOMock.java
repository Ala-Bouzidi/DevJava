
package com.stock.stockmasterpro.dao;

import com.stock.stockmasterpro.model.Produit;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOMock {
    public List<Produit> findAll() {
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit(1, "PC (MOCK)", 3500));
        produits.add(new Produit(2, "Clavier (MOCK)", 150));
        produits.add(new Produit(3, "Souris (MOCK)", 80));
        return produits;
    }
}