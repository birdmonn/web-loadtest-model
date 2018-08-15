package com.adss.rif.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ListToPaging {
    private static ListToPaging instance;
    public static final int[] PAGE_SIZES_SELECTION = {5, 10, 20};
    public static ListToPaging getInstance() {
        if (instance == null) {
            instance = new ListToPaging();
        }
        return instance;
    }

    public Page Paging(List result, int page, int pageSize) {
        if (page != 0) {
            page = page-1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        int start = Math.toIntExact(pageable.getOffset());
        int end = (start + pageable.getPageSize()) > result.size() ? result.size() : (start + pageable.getPageSize());
        if (start > end) {
            start = 0;
        }
        return new PageImpl(result.subList(start, end), pageable, result.size());
    }
}
