package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.UserWeb;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
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
    public String showForm() {
        return PathView.formCreate;
    }

    @GetMapping("/{id}")
    public String showForm(@PathVariable long id, Model model) {
        RequestForm requestForm = requestFormService.findById(id);
        if (requestForm == null) {
            return PathView.formCreate;
        }
        model.addAttribute("requestForm", requestForm);
        return PathView.formCreate;
    }

    @GetMapping("edit/{id}")
    public String test(@PathVariable long id, Model model,HttpServletRequest request) {
        RequestForm requestForm = requestFormService.findById(id);
        boolean roleuser = request.isUserInRole("USER");
        String userlogin = request.getRemoteUser();
        String userCreateForm = requestForm.getUserWeb().getUsername();
        if (requestForm == null ||(request.isUserInRole("USER") && !request.getRemoteUser().equals(requestForm.getUserWeb().getUsername()))) {
            return "redirect:/index";
        }
        model.addAttribute("requestForm", requestForm);
        return PathView.formEdit;
    }


    @PostMapping()
    public String checkPersonInfo(@Valid RequestForm requestForm, BindingResult bindingResult, HttpServletRequest request) {
        UserWeb userWeb = userWebService.findByUsername(request.getRemoteUser());
        if (bindingResult.hasErrors() || userWeb == null) {
            return PathView.formCreate;
        }
        requestForm.setUserWeb(userWeb);
        requestFormService.create(requestForm);
        return "redirect:"+PathView.searchCase;
    }

    @PostMapping("edit/{id}")
    public String updateRequestForm(@PathVariable long id,@Valid RequestForm requestForm, BindingResult bindingResult,Model model ) {

        if (bindingResult.hasErrors() || null == requestFormService.findById(id) || requestForm.getProjectName().equals("")) {
            return PathView.formEdit;
        }
        requestFormService.update(id, requestForm);
        return "redirect:"+PathView.searchCase;
    }
}
