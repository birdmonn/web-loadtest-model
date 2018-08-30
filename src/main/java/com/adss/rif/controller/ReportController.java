package com.adss.rif.controller;

import com.adss.rif.entities.DateQueryReport;
import com.adss.rif.entities.PagerModel;
import com.adss.rif.entities.RequestForm;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.ListToPaging;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RequestFormToTable;
import com.adss.rif.utils.RoleToViewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import static com.adss.rif.utils.ListToPaging.PAGE_SIZES_SELECTION;
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
    public String myCaseShow(Model model,
                             HttpServletRequest request,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             @RequestParam(value = "page", required = false, defaultValue = "0") int page) {

        // set dateSearch
        DateQueryReport dq = new DateQueryReport();
        model.addAttribute("dateQueryReport", dq);
        Date searchStartDate = new GregorianCalendar(now().getYear(), now().getMonthValue() - 1, 1).getTime();
        Date searchEndDate = new GregorianCalendar(now().getYear(), now().getMonthValue(), 1).getTime();
        model.addAttribute("searchStartDate", new SimpleDateFormat("dd/MM/yyyy").format(searchStartDate));
        model.addAttribute("searchEndDate", new SimpleDateFormat("dd/MM/yyyy").format(searchEndDate));
        // add data
        List<RequestForm> requestFormList = requestFormService.findByCreatedBetween(searchStartDate, searchEndDate);
        this.setConJob(model, requestFormList);
        RequestFormToTable.getInstance().setRequestFormListToTable(model, requestFormList, page, pageSize);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.report;
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
