package com.stock.stockmasterpro.dao;

import com.stock.stockmasterpro.model.Produit;
import java.util.List;
public interface ProduitDAO {
    List<Produit> findAll();
}
