package com.adss.rif.service;

import com.adss.rif.entities.ReliabilityTestScenario;
import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.StressTestScenario;

import java.util.List;

public interface StressTestScenarioService {

    List<StressTestScenario> findAll();

    StressTestScenario findById(Long id);

    StressTestScenario create(StressTestScenario stressTestScenario);

    void createAllList(List<StressTestScenario> stressTestScenarios, RequestForm requestForm);

    void updateStatusAllList(List<StressTestScenario> stressTestScenarioList);

    StressTestScenario update(Long id, StressTestScenario stressTestScenario);

    StressTestScenario deleteById(Long id);

}
