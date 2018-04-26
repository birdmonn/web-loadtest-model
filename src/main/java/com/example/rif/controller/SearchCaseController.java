package com.example.rif.controller;

import com.example.rif.entities.RequestForm;
import com.example.rif.service.RequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
        model.addAttribute("caseList", requestFormService.findAll());
        String username = request.getRemoteUser();
        boolean isadmin = request.isUserInRole("ADMIN");
        return "searchCase";
    }


    @PostMapping()
    public String qurey(@Valid RequestForm requestForm,Model model){
        model.addAttribute("caseList", requestFormService.findByProjectIdAndProjectNameAndContact(requestForm.getProjectId(),requestForm.getProjectName(),requestForm.getContact()));
        return "searchCase";
    }
}
