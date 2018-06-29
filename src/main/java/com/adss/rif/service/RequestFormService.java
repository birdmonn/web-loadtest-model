package com.adss.rif.service;

import com.adss.rif.entities.RequestForm;

import java.util.List;

public interface RequestFormService {

    List<RequestForm> findAll();

    RequestForm findById(Long id);

    List<RequestForm> findByCreateByUser(String createByUser);

    List<RequestForm> findByCrateByUserAndDepartment(String createByUser,String department);

    List<RequestForm> findByProjectIdAndProjectNameAndContact(String projectId, String projectName, String contact);

    RequestForm create(RequestForm requestForm);

    RequestForm update(Long id, RequestForm requestForm);

    RequestForm deleteById(Long id);

    RequestForm updateStatusScenario(RequestForm requestForm);

}
