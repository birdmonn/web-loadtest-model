package com.adss.rif.controller;

import com.adss.rif.entities.UserWeb;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String openview(UserWeb userWeb, Model model) {
        return PathView.register;
    }

    @PostMapping()
    public String createUser(@Valid UserWeb userWeb, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userWeb", userWeb);
            return PathView.register;
        }
        UserWeb checkUserName = userWebService.findByUsername(userWeb.getUsername());
        if (checkUserName != null) {
            FieldError fieldError = new FieldError("username", "username", "User Duplicate.");
            bindingResult.addError(fieldError);
            model.addAttribute("userWeb", userWeb);
            return PathView.register;
        }
        userWeb.setRole("USER");
        userWeb.setPassword(new BCryptPasswordEncoder().encode(userWeb.getPassword()));
        userWebService.create(userWeb);
        return PathView.login;
    }
}
