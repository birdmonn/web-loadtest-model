package com.adss.rif.service;

import com.adss.rif.entities.UserWeb;

import java.util.List;

public interface UserWebService {

    List<UserWeb> findAll();

    UserWeb findById(Long id);

    UserWeb findByUsername(String username);

    UserWeb create(UserWeb userWeb);

    UserWeb update(Long id, UserWeb userWeb);

    UserWeb deleteById(Long id);

}
