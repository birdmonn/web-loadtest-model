package com.adss.rif.controller;

import com.adss.rif.entities.UserWeb;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/userView")
public class UserViewController {
    private UserWebService userWebService;

    @Autowired
    public UserViewController(UserWebService userWebService) {
        this.userWebService = userWebService;
    }

    @GetMapping("/{id}")
    public String allList(@PathVariable long id,Model model, HttpServletRequest request) {
        if (!request.isUserInRole("ADMIN")) {
            return "redirect:" + PathView.index;
        }
//        List<UserWeb> userWebList = userWebService.findAll();
//        model.addAttribute("userList", userWebService.findAll());
        return PathView.userView;
    }
}
