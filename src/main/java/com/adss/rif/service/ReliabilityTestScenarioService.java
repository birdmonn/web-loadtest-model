package com.adss.rif.service;

import com.adss.rif.entities.ReliabilityTestScenario;
import com.adss.rif.entities.RequestForm;

import java.util.List;

public interface ReliabilityTestScenarioService {

    List<ReliabilityTestScenario> findAll();

    ReliabilityTestScenario findById(Long id);

    ReliabilityTestScenario create(ReliabilityTestScenario reliabilityTestScenario);

    void createAllList(List<ReliabilityTestScenario> reliabilityTestScenarioList, RequestForm requestForm);

    ReliabilityTestScenario update(Long id, ReliabilityTestScenario reliabilityTestScenario);

    ReliabilityTestScenario deleteById(Long id);

}
