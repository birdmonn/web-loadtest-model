package com.adss.rif.config;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
