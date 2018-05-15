package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.service.*;
import com.adss.rif.utils.PathView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/form")
public class ViewFormController {

    private RequestFormService requestFormService;
    @Autowired
    public ViewFormController(RequestFormService requestFormService) {
        this.requestFormService = requestFormService;
    }

    @GetMapping("/formView/{id}")
    public String viewFormById(@PathVariable Long id, Model model) {
        RequestForm requestForm = requestFormService.findById(id);
        if (requestForm == null) {
            return PathView.index;
        }
        model.addAttribute("requestForm", requestForm);
        return PathView.formView;
    }
}
