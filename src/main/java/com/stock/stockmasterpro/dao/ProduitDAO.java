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

        System.out.println("=== ProduitDAO.findAll() ===");
        System.out.println("URL: " + url);
        System.out.println("Username: " + username);

        try {
            // Explicitly load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✓ MySQL Driver loaded successfully");

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("✓ Database connection established");

                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    System.out.println("✓ Prepared statement created");
                    System.out.println("SQL: " + sql);

                    try (ResultSet rs = ps.executeQuery()) {
                        System.out.println("✓ Query executed successfully");

                        while (rs.next()) {
                            Produit p = new Produit();
                            p.setId(rs.getInt("id"));
                            p.setNom(rs.getString("nom"));
                            p.setPrix(rs.getDouble("prix"));
                            liste.add(p);
                            System.out.println("  → Loaded: " + p.getNom() + " (ID: " + p.getId() + ", Price: " + p.getPrix() + ")");
                        }

                        System.out.println("✓ Total products loaded: " + liste.size());
                    }
                }
            } catch (SQLException e) {
                System.err.println("✗ SQL Error: " + e.getMessage());
                System.err.println("  Error Code: " + e.getErrorCode());
                System.err.println("  SQL State: " + e.getSQLState());
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("✗ MySQL Driver not found!");
            System.err.println("  Make sure mysql-connector-j is in WEB-INF/lib");
            e.printStackTrace();
        }

        System.out.println("=== Returning " + liste.size() + " products ===\n");
        return liste;
    }
}