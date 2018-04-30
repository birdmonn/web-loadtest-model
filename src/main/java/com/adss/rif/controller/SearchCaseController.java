package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.SearchForm;
import com.adss.rif.service.RequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    public SearchCaseController(RequestFormService requestFormService) {
        this.requestFormService = requestFormService;
    }
    @GetMapping()
    public String allList(Model model, HttpServletRequest request){
        List<RequestForm> requestFormList = requestFormService.findAll();
        model.addAttribute("caseList", requestFormService.findAll());
//        String username = request.getRemoteUser();
//        boolean isadmin = request.isUserInRole("ADMIN");
        return "searchCase";
    }


    @PostMapping()
    public String qurey(@Valid SearchForm searchForm, Model model){
        model.addAttribute("caseList", requestFormService.findByProjectIdAndProjectNameAndContact(searchForm.getProjectId(),searchForm.getProjectName(),searchForm.getContact()));
        return "searchCase";
    }
}
