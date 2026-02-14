package com.stock.stockmasterpro.service;

public class AuthService {
    public boolean authentifier(String login, String password) {
        // Mock - À remplacer plus tard par base de données
        return "admin".equals(login) && "123".equals(password);
    }
}
