package com.adss.rif.config;

import com.adss.rif.entities.UserWeb;
import com.adss.rif.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserWebService userWebService;

    @Autowired
    public UserDetailsServiceImpl(UserWebService userWebService) {
        this.userWebService = userWebService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWeb userWeb = userWebService.findByUsername(username);
        return new User(userWeb.getUsername(), userWeb.getPassword(), getRole(userWeb.getRole()));
    }

    private List<SimpleGrantedAuthority> getRole(String role) {
        List<SimpleGrantedAuthority> roleList = new ArrayList<>();
        roleList.add(new SimpleGrantedAuthority("ROLE_" + role));
        return roleList;
    }
}
