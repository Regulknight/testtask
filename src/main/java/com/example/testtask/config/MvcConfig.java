package com.example.testtask.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lobachev.nikolay 29.03.2020   23:42
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
        if (registry != null) {
            registry.addViewController("/login").setViewName("login");
        }
    }

}
