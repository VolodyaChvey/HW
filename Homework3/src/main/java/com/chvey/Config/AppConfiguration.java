package com.chvey.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.chvey"})
@PropertySource("classpath:db/db.properties")
public class AppConfiguration {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("scripts/createTables.sql")
                .addScript("scripts/insertProducts.sql")
                .setScriptEncoding("UTF-8")
                .continueOnError(true)
                .ignoreFailedDrops(true)
                .build();
    }

    @Bean
    Connection connection() {
        try {
            return dataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
