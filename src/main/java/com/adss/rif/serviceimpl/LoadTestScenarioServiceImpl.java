package com.adss.rif.serviceimpl;

import com.adss.rif.entities.LoadTestScenario;
import com.adss.rif.entities.RequestForm;
import com.adss.rif.repository.LoadTestScenarioRepository;
import com.adss.rif.service.LoadTestScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadTestScenarioServiceImpl implements LoadTestScenarioService {
    private LoadTestScenarioRepository loadTestScenarioRepository;

    @Autowired
    public LoadTestScenarioServiceImpl(LoadTestScenarioRepository loadTestScenarioRepository) {
        this.loadTestScenarioRepository = loadTestScenarioRepository;
    }

    @Override
    public List<LoadTestScenario> findAll() {
        return loadTestScenarioRepository.findAll();
    }

    @Override
    public LoadTestScenario findById(Long id) {
        return loadTestScenarioRepository.getOne(id);
    }

    @Override
    public void updateStatusAllList(List<LoadTestScenario> loadTestScenarioList) {
        for (LoadTestScenario loadItem : loadTestScenarioList){
            LoadTestScenario loadQuery = loadTestScenarioRepository.getOne(loadItem.getId());
            loadQuery.setScenarioPass(loadItem.isScenarioPass());
            loadTestScenarioRepository.saveAndFlush(loadQuery);
        }
    }

    @Override
    public LoadTestScenario create(LoadTestScenario loadTestScenario) {
        return loadTestScenarioRepository.saveAndFlush(loadTestScenario);
    }

    @Override
    public void createAllList(List<LoadTestScenario> loadTestScenarioList, RequestForm requestForm) {
        for (LoadTestScenario loadItem: loadTestScenarioList) {
            if(!loadItem.getDetail().equals("") && loadItem.getDetail() != null){
                loadItem.setRequestForm(requestForm);
                loadTestScenarioRepository.saveAndFlush(loadItem);
            }
        }
    }

    @Override
    public LoadTestScenario update(Long id, LoadTestScenario loadTestScenario) {
        return null;
    }

    @Override
    public LoadTestScenario deleteById(Long id) {
        return null;
    }
}
