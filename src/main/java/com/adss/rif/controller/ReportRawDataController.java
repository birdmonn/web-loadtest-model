package com.adss.rif.controller;

import com.adss.rif.entities.*;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RequestFormToTable;
import com.adss.rif.utils.RoleToViewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

@Controller
@RequestMapping("/reportRawData")

public class ReportRawDataController {

    private RequestFormService requestFormService;
    private UserWebService userWebService;

    @Autowired
    public ReportRawDataController(RequestFormService requestFormService,
                                   UserWebService userWebService) {
        this.requestFormService = requestFormService;
        this.userWebService = userWebService;
    }

    @GetMapping()
    public String ShowDefaultReport(Model model,
                                    HttpServletRequest request,
                                    @RequestParam(value = "startDate", required = false, defaultValue = "0") long startDate,
                                    @RequestParam(value = "endDate", required = false, defaultValue = "0") long endDate
    ) {

        // set dateSearch
        Date searchStartDate, searchEndDate;
        if (startDate != 0 && endDate != 0) {
            searchStartDate = new Date(startDate);
            searchEndDate = new Date(endDate);
        } else {
            searchStartDate = new GregorianCalendar(now().getYear(), now().getMonthValue() - 1, 1).getTime();
            searchEndDate = new GregorianCalendar(now().getYear(), now().getMonthValue(), 1).getTime();
        }
        model.addAttribute("dateQueryReport", new DateQueryReport(searchStartDate, searchEndDate));
        model.addAttribute("searchStartDate", new SimpleDateFormat("dd/MM/yyyy").format(searchStartDate));
        model.addAttribute("searchEndDate", new SimpleDateFormat("dd/MM/yyyy").format(searchEndDate));
        // add data
        List<RequestForm> requestFormList = requestFormService.findByCreatedBetween(searchStartDate, searchEndDate);
        this.setScenario(model, requestFormList);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        model.addAttribute("formList", requestFormList);
        return PathView.reportRawData;
    }

    @PostMapping()
    public String showReportByDateSelect(@Valid DateQueryReport dateQueryReport) {
        return "redirect:/" + PathView.reportRawData + "?startDate=" + (dateQueryReport.getStartDate().getTime()) + "&endDate=" + (dateQueryReport.getEndDate().getTime());
    }

    private void setScenario(Model model, List<RequestForm> requestFormList) {
        List<LoadTestScenario> loadTestScenarioList = new ArrayList<>();
        List<StressTestScenario> stressTestScenarioList = new ArrayList<>();
        List<ReliabilityTestScenario> reliabilityTestScenarioList = new ArrayList<>();
        for (RequestForm item : requestFormList) {
            loadTestScenarioList.addAll(item.getLoadTestScenarioList());
            stressTestScenarioList.addAll(item.getStressTestScenarioList());
            reliabilityTestScenarioList.addAll(item.getReliabilityTestScenarioList());
        }
        model.addAttribute("loadTestList", loadTestScenarioList);
        model.addAttribute("stressTestList", stressTestScenarioList);
        model.addAttribute("reliabilityTestList", reliabilityTestScenarioList);
    }
}
