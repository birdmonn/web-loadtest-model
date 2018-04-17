package com.example.rif.service;

import com.example.rif.entities.RequestForm;

import java.util.List;

public interface RequestFormService {

    List<RequestForm> findAll();

    RequestForm findById(Long id);

    List<RequestForm> findByProjectIdAndProjectNameAndContact(String projectId,String projectName,String contact);

    RequestForm create(RequestForm requestForm);

    RequestForm update(Long id, RequestForm requestForm);

    RequestForm deleteById(Long id);

}
