package com.stock.stockmasterpro.service;

public class AuthService {
    public boolean authentifier(String login, String password) {

        return "admin".equals(login) && "123".equals(password);
    }
}
