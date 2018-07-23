package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.SearchForm;
import com.adss.rif.entities.UserWeb;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RoleToViewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/userManagement")
public class UserMangementController {
    private UserWebService userWebService;

    @Autowired
    public UserMangementController(UserWebService userWebService) {
        this.userWebService = userWebService;
    }

    @GetMapping()
    public String allList(Model model, HttpServletRequest request) {
        if (!request.isUserInRole("ADMIN")) {
            return "redirect:" + PathView.index;
        }
        List<UserWeb> userWebList = userWebService.findAll();
        model.addAttribute("userList", userWebService.findAll());
        RoleToViewPage.getInstance().roleUser(model,request.getRemoteUser(),userWebService);
        return PathView.userManagement;
    }
}
