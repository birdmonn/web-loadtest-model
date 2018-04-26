package com.example.rif;

import com.example.rif.entities.UserWeb;
import com.example.rif.service.UserWebService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RifApplication {
    @Autowired
    private UserWebService userWebService;

    public static void main(String[] args) {
        SpringApplication.run(RifApplication.class, args);
    }

    @Bean
    InitializingBean initUserAdmin() {
        if (userWebService.findByUsername("superadmin") == null) {
            UserWeb userWeb = new UserWeb();
            userWeb.setUsername("superadmin");
            userWeb.setPassword(new BCryptPasswordEncoder().encode("superadmin"));
            userWeb.setFirstName("superadmin");
            userWeb.setLastName("admin");
            userWeb.setRole("Admin");
            userWebService.create(userWeb);
        }
        return null;
    }
}
