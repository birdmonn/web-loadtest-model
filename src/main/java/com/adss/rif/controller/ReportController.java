package com.adss.rif.controller;

import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RoleToViewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/report")

public class ReportController {

    private RequestFormService requestFormService;
    private UserWebService userWebService;

    @Autowired
    public ReportController(RequestFormService requestFormService,
                           UserWebService userWebService) {
        this.requestFormService = requestFormService;
        this.userWebService = userWebService;
    }
    @GetMapping()
    public String myCaseShow(Model model,
                             HttpServletRequest request,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             @RequestParam(value = "page", required = false, defaultValue = "0") int page) {

//        this.setModelIndex(model, request, page, pageSize);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.report;
    }
}
