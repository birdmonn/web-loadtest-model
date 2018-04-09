package com.example.rif.controller;

import com.example.rif.service.RequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search-case")
public class SearchCaseController {
    private RequestFormService requestFormService;

    @Autowired
    public SearchCaseController(RequestFormService requestFormService) {
        this.requestFormService = requestFormService;
    }
    @GetMapping()
    public String list(Model model){
        model.addAttribute("caseList", requestFormService.findall());
        return "search-case";
    }
}
