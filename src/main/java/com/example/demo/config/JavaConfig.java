package com.example.demo.config;

import com.example.demo.calculator.Add;
import com.example.demo.calculator.AddResponse;
import com.example.demo.calculator.Calculator;
import com.example.demo.entities.Department;
import com.example.demo.entities.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.repositories")
public class JavaConfig {

    @Value("${spring.datasource.username}")
    private String bdUserName;

    @Value("${spring.datasource.password}")
    private String bdUserPass;

    @Value("${spring.datasource.url}")
    private String bdUserUrl;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        dataSource.setUsername(bdUserName);
        dataSource.setPassword(bdUserPass);
        dataSource.setUrl(bdUserUrl);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.example.demo.entities");
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public AddResponse addResponse() {
        return new AddResponse();
    }

    @Bean
    public Add add() {
        return new Add();
    }

    @Bean
    public Calculator calculator() {
        return new Calculator();
    }

    @Bean
    public Person person() {
        return new Person();
    }

    @Bean
    public Department department() {
        return new Department();
    }
}
