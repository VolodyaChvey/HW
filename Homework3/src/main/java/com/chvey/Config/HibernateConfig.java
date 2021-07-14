package com.chvey.Config;


import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
public class HibernateConfig {
    @Autowired
    private Environment env;
    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setAnnotatedClasses(User.class, Product.class, Order.class);
        factoryBean.setHibernateProperties(hibernateProperties());
        factoryBean.setDataSource(dataSource());
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionMeneger(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
    private Properties hibernateProperties(){
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    properties.setProperty("hibernate.hbm2ddl.import_files", env.getProperty("hibernate.hbm2ddl.import_files"));
    properties.setProperty("hibernate.hbm2ddl.import_files_sql_extractor",env.getProperty("hibernate.hbm2ddl.import_files_sql_extractor"));
    return properties;
    }
  @Bean
    public DataSource dataSource(){
      BasicDataSource dataSource =new BasicDataSource();
      dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
      dataSource.setUrl(env.getProperty("jdbc.url"));
      dataSource.setUsername(env.getProperty("jdbc.user"));
      dataSource.setPassword(env.getProperty("jdbc.pass"));
      return dataSource;
  }
}
