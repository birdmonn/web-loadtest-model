package com.adss.rif.serviceimpl;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.entities.StressTestScenario;
import com.adss.rif.repository.StressTestScenarioRepository;
import com.adss.rif.service.StressTestScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StressTestScenarioServiceImpl implements StressTestScenarioService {
    private StressTestScenarioRepository stressTestScenarioRepository;

    @Autowired
    public StressTestScenarioServiceImpl(StressTestScenarioRepository stressTestScenarioRepository) {
        this.stressTestScenarioRepository = stressTestScenarioRepository;
    }

    @Override
    public List<StressTestScenario> findAll() {
        return stressTestScenarioRepository.findAll();
    }

    @Override
    public StressTestScenario findById(Long id) {
        return stressTestScenarioRepository.getOne(id);
    }

    @Override
    public StressTestScenario create(StressTestScenario stressTestScenario) {
        return stressTestScenarioRepository.saveAndFlush(stressTestScenario);
    }

    @Override
    public void createAllList(List<StressTestScenario> stressTestScenarioList, RequestForm requestForm) {
        for (StressTestScenario stressItem: stressTestScenarioList) {
            if(!stressItem.getDetail().equals("") && stressItem.getDetail() != null){
                stressItem.setRequestForm(requestForm);
                stressTestScenarioRepository.saveAndFlush(stressItem);
            }
        }
    }

    @Override
    public StressTestScenario update(Long id, StressTestScenario stressTestScenario) {
        return null;
    }

    @Override
    public StressTestScenario deleteById(Long id) {
        return null;
    }

    @Override
    public void updateStatusAllList(List<StressTestScenario> stressTestScenarioList) {
        for (StressTestScenario stressItem : stressTestScenarioList){
            StressTestScenario stressQuery = stressTestScenarioRepository.getOne(stressItem.getId());
            stressQuery.setScenarioPass(stressItem.isScenarioPass());
            stressTestScenarioRepository.saveAndFlush(stressQuery);
        }
    }
}
