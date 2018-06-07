package com.adss.rif.controller;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.PathView;
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

    @Autowired
    public IndexController(RequestFormService requestFormService) {
        this.requestFormService = requestFormService;
    }

    @GetMapping("/index")
    public String myCaseShowIndex (Model model,HttpServletRequest request){

        List<RequestForm> requestFormList = requestFormService.findByUserWeb(request.getRemoteUser());
        model.addAttribute("caseList", requestFormList);
        return PathView.index;
    }

    @GetMapping("/")
    public String myCaseShowIndex2 (Model model,HttpServletRequest request){

        List<RequestForm> requestFormList = requestFormService.findByUserWeb(request.getRemoteUser());
        model.addAttribute("caseList", requestFormList);
        return PathView.index;
    }

    @GetMapping()
    public String myCaseShow (Model model,HttpServletRequest request){

        List<RequestForm> requestFormList = requestFormService.findByUserWeb(request.getRemoteUser());
        model.addAttribute("caseList", requestFormList);
        return PathView.index;
    }

}
