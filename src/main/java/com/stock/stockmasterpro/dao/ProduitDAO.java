package com.stock.stockmasterpro.dao;

import com.stock.stockmasterpro.model.Produit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    private String url = "jdbc:mysql://localhost:3306/stockmaster?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private String username = "root";
    private String password = "root";

    public List<Produit> findAll() {
        List<Produit> liste = new ArrayList<>();
        String sql = "SELECT * FROM produits";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Produit p = new Produit();
                    p.setId(rs.getInt("id"));
                    p.setNom(rs.getString("nom"));
                    p.setPrix(rs.getDouble("prix"));
                    liste.add(p);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return liste;
    }
}