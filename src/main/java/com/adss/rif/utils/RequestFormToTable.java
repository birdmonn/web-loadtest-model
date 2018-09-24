package com.adss.rif.utils;

import com.adss.rif.entities.PagerModel;
import com.adss.rif.entities.RequestForm;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;

import static com.adss.rif.utils.ListToPaging.PAGE_SIZES_SELECTION;

public class RequestFormToTable {
    private static RequestFormToTable instance;
    public static RequestFormToTable getInstance() {
        if (instance == null) {
            instance = new RequestFormToTable();
        }
        return instance;
    }

    public void setRequestFormListToTable(Model model,List<RequestForm> requestFormList, int page, int pageSize) {
        Page formList = ListToPaging.getInstance().Paging(requestFormList, page, pageSize);
        PagerModel pageModel = new PagerModel(formList.getTotalPages(), formList.getNumber(), 3);
        //add model
        // add ListData
        model.addAttribute("formList", formList);
        model.addAttribute("pager", pageModel);
        // evaluate page size
        model.addAttribute("selectedPageSize", pageSize);
        model.addAttribute("pageSizes", PAGE_SIZES_SELECTION);
    }
}
