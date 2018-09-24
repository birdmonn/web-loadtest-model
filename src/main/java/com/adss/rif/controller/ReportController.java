package com.adss.rif.controller;

import com.adss.rif.entities.DateQueryReport;
import com.adss.rif.entities.RequestForm;
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
@RequestMapping("/report")

public class ReportController {

    private RequestFormService requestFormService;
    private UserWebService userWebService;

    @Autowired
    public ReportController(RequestFormService requestFormService,
                            UserWebService userWebService) {
        this.requestFormService = requestFormService;
        this.userWebService = userWebService;
    }

    @GetMapping()
    public String ShowDefaultReport(Model model,
                             HttpServletRequest request,
                             @RequestParam(value = "startDate", required = false, defaultValue = "0") long startDate,
                             @RequestParam(value = "endDate", required = false, defaultValue = "0") long endDate,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             @RequestParam(value = "page", required = false, defaultValue = "0") int page) {

        // set dateSearch
        Date searchStartDate,searchEndDate;
        if (startDate !=0 && endDate !=0){
            searchStartDate = new Date(startDate);
            searchEndDate = new Date(endDate);
        } else {
            searchStartDate = new GregorianCalendar(now().getYear(), now().getMonthValue() - 1, 1).getTime();
            searchEndDate = new GregorianCalendar(now().getYear(), now().getMonthValue(), 1).getTime();
        }
        model.addAttribute("dateQueryReport", new DateQueryReport(searchStartDate,searchEndDate));
        model.addAttribute("searchStartDate", new SimpleDateFormat("dd/MM/yyyy").format(searchStartDate));
        model.addAttribute("searchEndDate", new SimpleDateFormat("dd/MM/yyyy").format(searchEndDate));
        // add data
        List<RequestForm> requestFormList = requestFormService.findByCreatedBetween(searchStartDate, searchEndDate);
        this.setConJob(model, requestFormList);
        RequestFormToTable.getInstance().setRequestFormListToTable(model, requestFormList, page, pageSize);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.report;
    }

    @PostMapping()
    public String showReportByDateSelect(@Valid DateQueryReport dateQueryReport){
        return "redirect:/"+ PathView.report+"?startDate=" +(dateQueryReport.getStartDate().getTime())+"&endDate="+(dateQueryReport.getEndDate().getTime());
    }

    private void setConJob(Model model, List<RequestForm> requestFormList) {
        List<RequestForm> formInProcess = requestFormList.stream()
                .filter(a -> Objects.equals(a.getStatusRequest(), "inProcess"))
                .collect(Collectors.toList());
        List<RequestForm> formSuccess = requestFormList.stream()
                .filter(a -> Objects.equals(a.getStatusRequest(), "success"))
                .collect(Collectors.toList());
        model.addAttribute("cJob", requestFormList.size());
        model.addAttribute("pJob", formInProcess.size());
        model.addAttribute("sJob", formSuccess.size());
    }
}
