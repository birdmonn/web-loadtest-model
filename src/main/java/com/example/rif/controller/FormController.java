package com.example.rif.controller;

import com.example.rif.entities.RequestForm;
import com.example.rif.service.RequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
@RequestMapping("/form")
public class FormController implements WebMvcConfigurer {

    private RequestFormService requestFormService;

    @Autowired
    public FormController(RequestFormService requestFormService) {
        this.requestFormService = requestFormService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping()
    public String showForm(RequestForm requestForm) {
        return "form";
    }

    @GetMapping("/{id}")
    public String showForm(@PathVariable long id, Model model) {
        RequestForm requestForm = requestFormService.findById(id);
        if (requestForm == null) {
            return "form";
        }
        model.addAttribute("requestForm", requestForm);
        return "form";
    }

    @PostMapping()
    public String checkPersonInfo(@Valid RequestForm requestForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        System.out.print(requestForm.getProjectName());
        requestFormService.create(requestForm);
        return "redirect:/all-case";
    }

    @PostMapping("/{id}")
    public String updateRequestForm(@Valid RequestForm requestForm, @PathVariable long id, BindingResult bindingResult) {

        if (bindingResult.hasErrors() && null == requestFormService.findById(id)) {
            return "form";
        }

        System.out.print(requestForm.getProjectName());
        requestFormService.update(id, requestForm);
        return "redirect:/all-case";
    }
}
