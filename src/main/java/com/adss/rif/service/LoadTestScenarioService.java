package com.adss.rif.service;

import com.adss.rif.entities.LoadTestScenario;
import com.adss.rif.entities.RequestForm;

import java.util.List;

public interface LoadTestScenarioService {

    List<LoadTestScenario> findAll();

    LoadTestScenario findById(Long id);

    LoadTestScenario create(LoadTestScenario loadTestScenario);

    void createAllList(List<LoadTestScenario> loadTestScenarioList, RequestForm requestForm);

    void updateStatusAllList(List<LoadTestScenario> loadTestScenarioList);

    LoadTestScenario update(Long id, LoadTestScenario loadTestScenario);

    LoadTestScenario deleteById(Long id);

}
