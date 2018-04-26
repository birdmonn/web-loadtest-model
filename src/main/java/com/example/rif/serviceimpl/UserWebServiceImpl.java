package com.example.rif.serviceimpl;

import com.example.rif.entities.RequestForm;
import com.example.rif.entities.UserWeb;
import com.example.rif.repository.RequestFormRepository;
import com.example.rif.repository.UserWebRepository;
import com.example.rif.service.RequestFormService;
import com.example.rif.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWebServiceImpl implements UserWebService {
    private UserWebRepository userWebRepository;

    @Autowired
    public UserWebServiceImpl(UserWebRepository userWebRepository) {
        this.userWebRepository = userWebRepository;
    }

    @Override
    public List<UserWeb> findAll() {
        return userWebRepository.findAll();
    }

    @Override
    public UserWeb findById(Long id) {
        return userWebRepository.getOne(id);
    }

    @Override
    public UserWeb findByUsername(String username) {
        return userWebRepository.findByUsername(username);
    }

    @Override
    public UserWeb create(UserWeb userWeb) {
        return userWebRepository.saveAndFlush(userWeb);
    }

    @Override
    public UserWeb update(Long id, UserWeb userWeb) {
        userWeb.setId(id);
        return userWebRepository.saveAndFlush(userWeb);
    }

    @Override
    public UserWeb deleteById(Long id) {
        return null;
    }
}
