package com.adss.rif;

import com.adss.rif.service.UserWebService;
import com.adss.rif.entities.UserWeb;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

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
            userWeb.setRole("ADMIN");
            userWebService.create(userWeb);
        }
        return null;
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }


}
