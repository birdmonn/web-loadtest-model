package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.SearchForm;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RoleToViewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/searchCase")
public class SearchCaseController {
    private RequestFormService requestFormService;
    private UserWebService userWebService;

    @Autowired
    public SearchCaseController(RequestFormService requestFormService,
                                UserWebService userWebService) {
        this.requestFormService = requestFormService;
        this.userWebService = userWebService;
    }

    @GetMapping()
    public String allList(Model model,HttpServletRequest request) {
        List<RequestForm> requestFormList = requestFormService.findAll();
        model.addAttribute("caseList", requestFormService.findAll());
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.searchCase;
    }

    @PostMapping()
    public String qurey(@Valid SearchForm searchForm, Model model ,HttpServletRequest request) {
        model.addAttribute("caseList", requestFormService.findByProjectIdAndProjectNameAndContact(searchForm.getProjectId(), searchForm.getProjectName(), searchForm.getContact()));
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.searchCase;
    }
}
