package com.chvey;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.chvey"})
@PropertySource("classpath:db/db.properties")
public class AppConfiguration {
}
