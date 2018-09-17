package com.adss.rif.controller;

import com.adss.rif.entities.PagerModel;
import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.SearchForm;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Base64;

import static com.adss.rif.utils.ListToPaging.PAGE_SIZES_SELECTION;

@Controller
@RequestMapping("/searchCase")
public class SearchCaseController {
    private RequestFormService requestFormService;
    private UserWebService userWebService;

    @Autowired
    public SearchCaseController(RequestFormService requestFormService,
                                UserWebService userWebService) {
        this.requestFormService = requestFormService;
        this.userWebService = userWebService;
    }

    @GetMapping()
    public String allList(Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "projectId", required = false, defaultValue = "") String projectId,
                          @RequestParam(value = "projectName", required = false, defaultValue = "") String projectName,
                          @RequestParam(value = "contact", required = false, defaultValue = "") String contact,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page) throws UnsupportedEncodingException {
        List<RequestForm> requestFormList = requestFormService.findByProjectIdAndProjectNameAndContact(decodeThai(projectId), decodeThai(projectName), decodeThai(contact));
        Page formList = ListToPaging.getInstance().Paging(requestFormList, page, pageSize);
        PagerModel pageModel = new PagerModel(formList.getTotalPages(), formList.getNumber(), 3);
        //add model
        // add ListData
        model.addAttribute("formList", formList);
        model.addAttribute("pager", pageModel);
        // evaluate page size
        model.addAttribute("selectedPageSize", pageSize);
        model.addAttribute("pageSizes", PAGE_SIZES_SELECTION);
        RoleToViewPage.getInstance().roleUser(model, request.getRemoteUser(), userWebService);
        return PathView.searchCase;
    }

    @PostMapping()
    public String qurey(@Valid SearchForm searchForm, HttpServletResponse response) throws UnsupportedEncodingException {
        return "redirect:/" + PathView.searchCase + "?projectId=" + encodeThai(searchForm.getProjectId()) + "&projectName=" + encodeThai(searchForm.getProjectName()) + "&contact=" + encodeThai(searchForm.getContact());
    }

    private String encodeThai(String encodeString) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(encodeString.getBytes("UTF-8"));
    }

    private String decodeThai(String decodeString) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(decodeString), "UTF-8");
    }
}
