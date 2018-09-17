package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.UserWeb;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RoleToViewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/userView")
public class UserViewController {
    private UserWebService userWebService;
    private RequestFormService requestFormService;

    @Autowired
    public UserViewController(UserWebService userWebService,
                              RequestFormService requestFormService) {
        this.userWebService = userWebService;
        this.requestFormService = requestFormService;
    }

    @GetMapping("/{id}")
    public String allList(@PathVariable long id, Model model, HttpServletRequest request) {
        if (!userWebService.findByUsername(request.getRemoteUser()).getRole().equals("ADMIN")) {
            return "redirect:/" + PathView.index;
        }
        UserWeb userWeb = userWebService.findById(id);
        List<RequestForm> requestFormList = requestFormService.findByCreateByUser(userWeb.getUsername());
        model.addAttribute("user", userWeb);
        model.addAttribute("caseList", requestFormList);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.userView;
    }

    @PostMapping("/changeRole")
    public String changeRoleUser(@RequestParam String role, @RequestParam Long userId, HttpServletRequest request) {
        if (!userWebService.findByUsername(request.getRemoteUser()).getRole().equals("ADMIN")) {
            return "redirect:/" + PathView.index;
        }
        UserWeb userWeb = userWebService.findById(userId);
        userWeb.setRole(role);
        userWebService.update(userId, userWeb);
        return "redirect:/" + PathView.userView + "/" + userId;
    }
}
