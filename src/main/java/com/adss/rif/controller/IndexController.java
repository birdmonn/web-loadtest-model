package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RoleToViewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
public class IndexController {

    private RequestFormService requestFormService;
    private UserWebService userWebService;

    @Autowired
    public IndexController(RequestFormService requestFormService,
                           UserWebService userWebService) {
        this.requestFormService = requestFormService;
        this.userWebService = userWebService;
    }

    @GetMapping("/index")
    public String myCaseShowIndex(Model model, HttpServletRequest request) {
        List<RequestForm> requestFormList = requestFormService.findByCrateByUserAndDepartment(request.getRemoteUser(),userWebService.findByUsername(request.getRemoteUser()).getDepartment());
        model.addAttribute("caseList", requestFormList);
        RoleToViewPage.getInstance().roleUser(model,request.getRemoteUser(),userWebService);
        return PathView.index;
    }

    @GetMapping("/")
    public String myCaseShowIndex2(Model model, HttpServletRequest request) {
        List<RequestForm> requestFormList = requestFormService.findByCrateByUserAndDepartment(request.getRemoteUser(),userWebService.findByUsername(request.getRemoteUser()).getDepartment());
        model.addAttribute("caseList", requestFormList);
        RoleToViewPage.getInstance().roleUser(model,request.getRemoteUser(),userWebService);
        return PathView.index;
    }

    @GetMapping()
    public String myCaseShow(Model model, HttpServletRequest request) {
        List<RequestForm> requestFormList = requestFormService.findByCrateByUserAndDepartment(request.getRemoteUser(),userWebService.findByUsername(request.getRemoteUser()).getDepartment());
        model.addAttribute("caseList", requestFormList);
        RoleToViewPage.getInstance().roleUser(model,request.getRemoteUser(),userWebService);
        return PathView.index;
    }

}
