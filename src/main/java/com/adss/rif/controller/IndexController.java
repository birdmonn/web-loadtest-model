package com.adss.rif.controller;

import com.adss.rif.entities.PagerModel;
import com.adss.rif.entities.RequestForm;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.service.UserWebService;
import com.adss.rif.utils.ListToPaging;
import com.adss.rif.utils.PathView;
import com.adss.rif.utils.RoleToViewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.adss.rif.utils.ListToPaging.PAGE_SIZES_SELECTION;

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
    public String myCaseShowIndex(Model model,
                                  HttpServletRequest request,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                  @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        this.setModelIndex(model, request, page, pageSize);
        return PathView.index;
    }

    @GetMapping("/")
    public String myCaseShowIndex2(Model model,
                                   HttpServletRequest request,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                   @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        this.setModelIndex(model, request, page, pageSize);
        return PathView.index;
    }

    @GetMapping()
    public String myCaseShow(Model model,
                             HttpServletRequest request,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             @RequestParam(value = "page", required = false, defaultValue = "0") int page) {

        this.setModelIndex(model, request, page, pageSize);
        return PathView.index;
    }

    private void setModelIndex(Model model, HttpServletRequest request, int page, int pageSize) {
        List<RequestForm> requestFormList;
        if (request.getRemoteUser().equals("superadmin")){
            requestFormList = requestFormService.findAll();
        } else {
            requestFormList = requestFormService.findByCrateByUserAndDepartment(request.getRemoteUser(), userWebService.findByUsername(request.getRemoteUser()).getDepartment());
        }
        Page formList = ListToPaging.getInstance().Paging(requestFormList, page, pageSize);
        PagerModel pageModel = new PagerModel(formList.getTotalPages(), formList.getNumber(), 3);
        //add model
        // add ListData
        model.addAttribute("formList", formList);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        model.addAttribute("pager", pageModel);
        // evaluate page size
        model.addAttribute("selectedPageSize", pageSize);
        model.addAttribute("pageSizes", PAGE_SIZES_SELECTION);
    }
}
