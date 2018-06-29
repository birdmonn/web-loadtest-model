package com.adss.rif.serviceimpl;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.repository.RequestFormRepository;
import com.adss.rif.service.RequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RequestFormServiceImpl implements RequestFormService {
    private RequestFormRepository requestFormRepository;

    @Autowired
    public RequestFormServiceImpl(RequestFormRepository requestFormRepository) {
        this.requestFormRepository = requestFormRepository;
    }

    @Override
    public List<RequestForm> findAll() {
        return requestFormRepository.findAll();
    }

    @Override
    public RequestForm findById(Long id) {
        return requestFormRepository.findAsId(id);
    }

    @Override
    public List<RequestForm> findByCreateByUser(String createByUser) {
        return requestFormRepository.findByCreateByUser(createByUser);
    }

    @Override
    public List<RequestForm> findByProjectIdAndProjectNameAndContact(String projectId, String projectName, String contact) {
        if (projectId.trim().equals("") && projectName.trim().equals("") && !contact.trim().equals("")) {
            return requestFormRepository.findByContact(contact);
        }
        if (projectId.trim().equals("") && !projectName.trim().equals("") && contact.trim().equals("")) {
            return requestFormRepository.findByProjectName(projectName);
        }
        if (!projectId.trim().equals("") && projectName.trim().equals("") && contact.trim().equals("")) {
            return requestFormRepository.findByProjectId(projectId);
        }
        if (projectId.trim().equals("") && !projectName.trim().equals("") && !contact.trim().equals("")) {
            return requestFormRepository.findByProjectNameAndContact(projectName, contact);
        }
        if (!projectId.trim().equals("") && !projectName.trim().equals("") && contact.trim().equals("")) {
            return requestFormRepository.findByProjectIdAndProjectName(projectId, projectName);
        }
        if (!projectId.trim().equals("") && projectName.trim().equals("") && !contact.trim().equals("")) {
            return requestFormRepository.findByProjectIdAndContact(projectId, contact);
        }
        return requestFormRepository.findByProjectIdAndProjectNameAndContact(projectId, projectName, contact);
    }

    @Override
    public RequestForm create(RequestForm requestForm) {
        return requestFormRepository.saveAndFlush(requestForm);
    }

    @Override
    public RequestForm update(Long id, RequestForm requestForm) {
        return requestFormRepository.saveAndFlush(requestForm);
    }

    @Override
    public RequestForm deleteById(Long id) {
        return null;
    }

    @Override
    public RequestForm updateStatusScenario(RequestForm requestForm) {
        RequestForm dataFormOriginal = requestFormRepository.findAsId(requestForm.getId());
        dataFormOriginal.setStatusRequest(requestForm.getStatusRequest());
        dataFormOriginal.setLoadTestScenarioList(requestForm.getLoadTestScenarioList());
        dataFormOriginal.setReliabilityTestScenarioList(requestForm.getReliabilityTestScenarioList());
        dataFormOriginal.setStressTestScenarioList(requestForm.getStressTestScenarioList());
        return requestFormRepository.saveAndFlush(dataFormOriginal);
    }

    @Override
    public List<RequestForm> findByCrateByUserAndDepartment(String createByUser, String department) {
        List<RequestForm> requestFormList = requestFormRepository.findByCreateByUser(createByUser);
        requestFormList.addAll(requestFormRepository.findByDepartment(department));
        return sortCreateDate(requestFormList);
    }

    private List<RequestForm> sortCreateDate(List<RequestForm> requestFormList){
        requestFormList.sort(Comparator.comparing(RequestForm::getCreated).reversed());
        return requestFormList;
    }
}
