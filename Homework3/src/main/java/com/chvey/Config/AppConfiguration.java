package com.chvey.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"com.chvey"})
@PropertySource("classpath:db/db.properties")
public class AppConfiguration {
    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(org.springframework.web.servlet.view.jstlView.class);
        viewResolver.setPrefix("WEB-IN/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
