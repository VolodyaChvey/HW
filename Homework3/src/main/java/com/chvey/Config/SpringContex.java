package com.chvey.Config;

import com.chvey.Config.AppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContex {
    public static ApplicationContext getApplicationContext() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        return applicationContext;
    }

}
