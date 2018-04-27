package com.example.rif.config;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
