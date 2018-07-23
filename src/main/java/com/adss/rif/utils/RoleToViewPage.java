package com.adss.rif.utils;

import com.adss.rif.service.UserWebService;
import org.springframework.ui.Model;

public class RoleToViewPage {
    private static RoleToViewPage instance;

    public static RoleToViewPage getInstance() {
        if (instance == null) {
            instance = new RoleToViewPage();
        }
        return instance;
    }

    public void roleUser(Model model, String username, UserWebService userWebService) {
        model.addAttribute("role", userWebService.findByUsername(username).getRole());
    }
}
