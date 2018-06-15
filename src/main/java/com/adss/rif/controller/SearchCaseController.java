package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.SearchForm;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.utils.PathView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String allList(Model model) {
        List<RequestForm> requestFormList = requestFormService.findAll();
        model.addAttribute("caseList", requestFormService.findAll());
        return PathView.searchCase;
    }

    @PostMapping()
    public String qurey(@Valid SearchForm searchForm, Model model) {
        model.addAttribute("caseList", requestFormService.findByProjectIdAndProjectNameAndContact(searchForm.getProjectId(), searchForm.getProjectName(), searchForm.getContact()));
        return PathView.searchCase;
    }
}
