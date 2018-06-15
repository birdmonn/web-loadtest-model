package com.adss.rif.controller;

import com.adss.rif.entities.UserWeb;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserWebService userWebService;

    @Autowired
    public RegisterController(UserWebService userWebService) {
        this.userWebService = userWebService;
    }

    @GetMapping()
    public String openview() {
        return "register";
    }

    @PostMapping()
    public String createUser(@Valid UserWeb userWeb, Model model) {
        userWeb.setRole("USER");
        userWeb.setPassword(new BCryptPasswordEncoder().encode(userWeb.getPassword()));
        userWebService.create(userWeb);
        return PathView.login;
    }
}
