package com.example.rif.controller;

import com.example.rif.entities.RequestForm;
import com.example.rif.entities.UserWeb;
import com.example.rif.service.RequestFormService;
import com.example.rif.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/form")
public class FormController implements WebMvcConfigurer {

    private RequestFormService requestFormService;
    private UserWebService userWebService;

    @Autowired
    public FormController(RequestFormService requestFormService,
                          UserWebService userWebService) {
        this.requestFormService = requestFormService;
        this.userWebService = userWebService;
    }

    @GetMapping()
    public String showForm(RequestForm requestForm) {
        return "form/formCreate";
    }

    @GetMapping("/{id}")
    public String showForm(@PathVariable long id, Model model) {
        RequestForm requestForm = requestFormService.findById(id);
        if (requestForm == null) {
            return "form/formCreate";
        }
        model.addAttribute("requestForm", requestForm);
        return "form/formCreate";
    }

    @GetMapping("edit/{id}")
    public String test(@PathVariable long id, Model model) {
        RequestForm requestForm = requestFormService.findById(id);
        if (requestForm == null) {
            return "form/formEdit";
        }
        model.addAttribute("requestForm", requestForm);
        return "form/formEdit";
    }


    @PostMapping()
    public String checkPersonInfo(@Valid RequestForm requestForm, BindingResult bindingResult, HttpServletRequest request) {
        UserWeb userWeb = userWebService.findByUsername(request.getRemoteUser());
        if (bindingResult.hasErrors() || userWeb == null) {
            return "form/formCreate";
        }
        requestForm.setUserWeb(userWeb);
        requestFormService.create(requestForm);
        return "redirect:/searchCase";
    }

    @PostMapping("edit/{id}")
    public String updateRequestForm(@PathVariable long id,@Valid RequestForm requestForm, BindingResult bindingResult,Model model ) {

        if (bindingResult.hasErrors() || null == requestFormService.findById(id) || requestForm.getProjectName().equals("")) {
            return "form/formEdit";
        }
        requestFormService.update(id, requestForm);
        return "redirect:/searchCase";
    }
}
