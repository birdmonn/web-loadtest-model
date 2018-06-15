package com.adss.rif.serviceimpl;

import com.adss.rif.entities.ReliabilityTestScenario;
import com.adss.rif.entities.RequestForm;
import com.adss.rif.repository.ReliabilityTestScenarioRepository;
import com.adss.rif.service.ReliabilityTestScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReliabilityTestScenarioServiceImpl implements ReliabilityTestScenarioService {
    private ReliabilityTestScenarioRepository reliabilityTestScenarioRepository;

    @Autowired
    public ReliabilityTestScenarioServiceImpl(ReliabilityTestScenarioRepository reliabilityTestScenarioRepository) {
        this.reliabilityTestScenarioRepository = reliabilityTestScenarioRepository;
    }

    @Override
    public List<ReliabilityTestScenario> findAll() {
        return reliabilityTestScenarioRepository.findAll();
    }

    @Override
    public ReliabilityTestScenario findById(Long id) {
        return reliabilityTestScenarioRepository.getOne(id);
    }

    @Override
    public ReliabilityTestScenario create(ReliabilityTestScenario reliabilityTestScenario) {
        return reliabilityTestScenarioRepository.saveAndFlush(reliabilityTestScenario);
    }

    @Override
    public void createAllList(List<ReliabilityTestScenario> reliabilityTestScenarioList, RequestForm requestForm) {
        for (ReliabilityTestScenario reliItem : reliabilityTestScenarioList) {
            if (!reliItem.getDetail().equals("") && reliItem.getDetail() != null) {
                reliItem.setRequestForm(requestForm);
                reliabilityTestScenarioRepository.saveAndFlush(reliItem);
            }
        }
    }

    @Override
    public void updateStatusAllList(List<ReliabilityTestScenario> reliabilityTestScenarioList) {
        for (ReliabilityTestScenario reliItem : reliabilityTestScenarioList) {
            ReliabilityTestScenario reliQuery = reliabilityTestScenarioRepository.getOne(reliItem.getId());
            reliQuery.setScenarioPass(reliItem.isScenarioPass());
            reliabilityTestScenarioRepository.saveAndFlush(reliQuery);
        }
    }

    @Override
    public ReliabilityTestScenario update(Long id, ReliabilityTestScenario reliabilityTestScenario) {
        return null;
    }

    @Override
    public ReliabilityTestScenario deleteById(Long id) {
        return null;
    }
}
