package com.example.rif.service;

import com.example.rif.entities.RequestForm;

import java.util.List;

public interface RequestFormService {

    List<RequestForm> findall();

    RequestForm findById(Long id);

    RequestForm create(RequestForm requestForm);

    RequestForm update(Long id, RequestForm requestForm);

    RequestForm deleteById(Long id);

}
