package com.example.rif.serviceimpl;

import com.example.rif.entities.RequestForm;
import com.example.rif.repository.RequestFormRepository;
import com.example.rif.service.RequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestFormServiceImpl implements RequestFormService {
    private RequestFormRepository requestFormRepository;

    @Autowired
    public RequestFormServiceImpl(RequestFormRepository requestFormRepository) {
        this.requestFormRepository = requestFormRepository;
    }

    @Override
    public List<RequestForm> findall() {
        return requestFormRepository.findAll();
    }

    @Override
    public RequestForm findById(Long id) {
        RequestForm requestForm123 = requestFormRepository.getOne(id);
        RequestForm requestForm = requestFormRepository.findAsId(id);
        return requestForm;
    }

    @Override
    public RequestForm create(RequestForm requestForm) {
        return requestFormRepository.saveAndFlush(requestForm);
    }

    @Override
    public RequestForm update(Long id, RequestForm requestForm) {
        return null;
    }

    @Override
    public RequestForm deleteById(Long id) {
        return null;
    }
}
