package com.adss.rif.serviceimpl;

import com.adss.rif.entities.RequestForm;
import com.adss.rif.repository.RequestFormRepository;
import com.adss.rif.service.RequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestFormServiceImpl implements RequestFormService {
    private RequestFormRepository requestFormRepository;

    @Autowired
    public RequestFormServiceImpl(RequestFormRepository requestFormRepository) {
        this.requestFormRepository = requestFormRepository;
    }

    @Override
    public List<RequestForm> findAll() {
        return sortCreateDate(requestFormRepository.findAll());
    }

    @Override
    public RequestForm findById(Long id) {
        return requestFormRepository.findAsId(id);
    }

    @Override
    public List<RequestForm> findByCreateByUser(String createByUser) {
        return sortCreateDate(requestFormRepository.findByCreateByUser(createByUser));
    }

    @Override
    public List<RequestForm> findByProjectIdAndProjectNameAndContactAndStatus(String projectId, String projectName, String contact,String status) {
        if (projectId.trim().equals("") && projectName.trim().equals("") && !contact.trim().equals("")) {
            return this.sortCreateDate(this.filterStatus(requestFormRepository.findByContact(contact),status));
        }
        if (projectId.trim().equals("") && !projectName.trim().equals("") && contact.trim().equals("")) {
            return this.sortCreateDate(this.filterStatus(requestFormRepository.findByProjectName(projectName),status));
        }
        if (!projectId.trim().equals("") && projectName.trim().equals("") && contact.trim().equals("")) {

            return this.sortCreateDate(this.filterStatus(requestFormRepository.findByProjectId(projectId),status));
        }
        if (projectId.trim().equals("") && !projectName.trim().equals("") && !contact.trim().equals("")) {
            return this.sortCreateDate(this.filterStatus(requestFormRepository.findByProjectNameAndContact(projectName, contact),status));
        }
        if (!projectId.trim().equals("") && !projectName.trim().equals("") && contact.trim().equals("")) {
            return this.sortCreateDate(this.filterStatus(requestFormRepository.findByProjectIdAndProjectName(projectId, projectName),status));
        }
        if (!projectId.trim().equals("") && projectName.trim().equals("") && !contact.trim().equals("")) {
            return this.sortCreateDate(this.filterStatus(requestFormRepository.findByProjectIdAndContact(projectId, contact),status));
        }
        return this.sortCreateDate(this.filterStatus(requestFormRepository.findByProjectIdAndProjectNameAndContact(projectId, projectName, contact),status));
    }

    @Override
    public RequestForm create(RequestForm requestForm) {
        return requestFormRepository.saveAndFlush(requestForm);
    }

    @Override
    public RequestForm update(Long id, RequestForm requestForm) {
        RequestForm formOld = findById(id);
        requestForm.setCreateByUser(formOld.getCreateByUser());
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
        requestFormList = requestFormList.stream().distinct().collect(Collectors.toList());
        return this.sortCreateDate(requestFormList);
    }

    private List<RequestForm> sortCreateDate(List<RequestForm> requestFormList){
        requestFormList.sort(Comparator.comparing(RequestForm::getCreated).reversed());
        return requestFormList;
    }

    private List<RequestForm> filterStatus(List<RequestForm> requestFormList,String status){
//        requestFormList.sort(Comparator.comparing(RequestForm::getCreated).reversed());
        if (status.equals("all") || status.equals("")){
            return requestFormList;
        }
        return requestFormList.stream().filter(requestForm->requestForm.getStatusRequest().equals(status)).collect(Collectors.toList());
    }

    @Override
    public List<RequestForm> findByCreatedBetween(Date firstDate, Date lastDate) {
        List<RequestForm> requestFormList = requestFormRepository.findByCreatedBetween(firstDate, lastDate);
        return requestFormList;
    }
}
