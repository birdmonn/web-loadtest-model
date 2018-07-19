package com.adss.rif.controller;

import com.adss.rif.entities.*;
import com.adss.rif.service.*;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RoleToViewPage;
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
    private LoadTestScenarioService loadTestScenarioService;
    private ReliabilityTestScenarioService reliabilityTestScenarioService;
    private StressTestScenarioService stressTestScenarioService;

    @Autowired
    public FormController(RequestFormService requestFormService,
                          UserWebService userWebService,
                          LoadTestScenarioService loadTestScenarioService,
                          ReliabilityTestScenarioService reliabilityTestScenarioService,
                          StressTestScenarioService stressTestScenarioService) {
        this.requestFormService = requestFormService;
        this.userWebService = userWebService;
        this.loadTestScenarioService = loadTestScenarioService;
        this.reliabilityTestScenarioService = reliabilityTestScenarioService;
        this.stressTestScenarioService = stressTestScenarioService;
    }

    @GetMapping("/formCreate")
    public String showForm(RequestForm requestForm, Model model, HttpServletRequest request) {
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.formCreate;
    }

    @GetMapping("/formCreate/{id}")
    public String showForm(@PathVariable long id, Model model, HttpServletRequest request) {
        RequestForm requestForm = requestFormService.findById(id);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        if (requestForm == null) {
            return PathView.formCreate;
        }
        model.addAttribute("requestForm", requestForm);
        return PathView.formCreate;
    }

    @GetMapping("formEdit/{id}")
    public String test(@PathVariable long id, Model model, HttpServletRequest request) {
        RequestForm requestForm = requestFormService.findById(id);
        if (requestForm == null || (request.isUserInRole("USER") && !request.getRemoteUser().equals(requestForm.getCreateByUser().getUsername()))) {
            return "redirect:" + PathView.index;
        }
        model.addAttribute("requestForm", requestForm);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.formEdit;
    }

    @PostMapping()
    public String createForm(@Valid RequestForm requestForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
        UserWeb userWeb = userWebService.findByUsername(request.getRemoteUser());
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        if (bindingResult.hasErrors() || userWeb == null) {
            return PathView.formCreate;
        }
        requestForm.setCreateByUser(userWeb);
        RequestForm formSave = requestFormService.create(requestForm);
        if (formSave != null) {
            loadTestScenarioService.createAllList(requestForm.getLoadTestScenarioList(), formSave);
            reliabilityTestScenarioService.createAllList(requestForm.getReliabilityTestScenarioList(), formSave);
            stressTestScenarioService.createAllList(requestForm.getStressTestScenarioList(), formSave);
            return "redirect:" + PathView.formView + "/" + formSave.getId();
        }
        return PathView.formCreate;
    }

    @PostMapping("formEdit/{id}")
    public String updateRequestForm(@PathVariable long id, @Valid RequestForm requestForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors() || null == requestFormService.findById(id) || requestForm.getProjectName().equals("")) {
            RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
            return PathView.formEdit;
        }
        requestFormService.update(id, requestForm);
        return "redirect:" + PathView.formView + "/" + id;
    }

    @PostMapping(value = "", params = {"addRowLoadTest"})
    public String addRowLoadTest(@RequestParam String page, RequestForm requestForm, Model model, HttpServletRequest request) {
        requestForm.getLoadTestScenarioList().add(new LoadTestScenario());
        model.addAttribute("requestForm", requestForm);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return "/form/" + page;
    }

    @PostMapping(value = "", params = {"addRowReliTest"})
    public String addRowReliTest(@RequestParam String page, RequestForm requestForm, Model model, HttpServletRequest request) {
        requestForm.getReliabilityTestScenarioList().add(new ReliabilityTestScenario());
        model.addAttribute("requestForm", requestForm);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return "/form/" + page;
    }

    @PostMapping(value = "", params = {"addRowStressTest"})
    public String addRowStressTest(@RequestParam String page, RequestForm requestForm, Model model, HttpServletRequest request) {
        requestForm.getStressTestScenarioList().add(new StressTestScenario());
        model.addAttribute("requestForm", requestForm);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return "/form/" + page;
    }
}
