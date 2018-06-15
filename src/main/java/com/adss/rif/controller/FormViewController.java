package com.adss.rif.controller;

import com.adss.rif.entities.FileReport;
import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.StressTestScenario;
import com.adss.rif.service.*;
import com.adss.rif.storage.StorageService;
import com.adss.rif.utils.PathView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/form")
public class FormViewController {

    private RequestFormService requestFormService;
    private LoadTestScenarioService loadTestScenarioService;
    private ReliabilityTestScenarioService reliabilityTestScenarioService;
    private StressTestScenarioService stressTestScenarioService;
    private final StorageService storageService;
    private final FileReportService fileReportService;

    @Autowired
    public FormViewController(RequestFormService requestFormService,
                              LoadTestScenarioService loadTestScenarioService,
                              ReliabilityTestScenarioService reliabilityTestScenarioService,
                              StressTestScenarioService stressTestScenarioService,
                              StorageService storageService,
                              FileReportService fileReportService) {
        this.requestFormService = requestFormService;
        this.loadTestScenarioService = loadTestScenarioService;
        this.reliabilityTestScenarioService = reliabilityTestScenarioService;
        this.stressTestScenarioService = stressTestScenarioService;
        this.storageService = storageService;
        this.fileReportService = fileReportService;

    }

    @GetMapping("/formView/{id}")
    public String viewFormById(@PathVariable Long id, Model model, HttpServletRequest request) {
        RequestForm requestForm = requestFormService.findById(id);
        if (requestForm == null) {
            return PathView.index;
        }
        model.addAttribute("requestForm", requestForm);
        if (request.isUserInRole("ADMIN")) {
            return PathView.formViewAdmin;
        }
        return PathView.formView;
    }

    @PostMapping("/formViewAdmin/{id}")
    public String editFormStatus(@RequestParam("file") MultipartFile[] fileList,@PathVariable Long id, RequestForm requestForm, Model model, HttpServletRequest request) {
        if (!request.isUserInRole("ADMIN") || (requestFormService.findById(id) == null)) {
            return "redirect:" + PathView.index;
        }
        stressTestScenarioService.updateStatusAllList(requestForm.getStressTestScenarioList());
        loadTestScenarioService.updateStatusAllList(requestForm.getLoadTestScenarioList());
        reliabilityTestScenarioService.updateStatusAllList(requestForm.getReliabilityTestScenarioList());
        RequestForm requestFormUpdate = requestFormService.updateStatusScenario(requestForm);
        for (MultipartFile file : fileList) {
            if (!file.getOriginalFilename().equals("")) {
                file.getOriginalFilename();
                String pathFile = storageService.store(file, "/" + requestForm.getId() + "/");
                fileReportService.create(new FileReport(pathFile, file.getOriginalFilename(), requestFormUpdate));
            }
        }
        return "redirect:" + PathView.formView + "/" + id;
    }


}
