package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {
//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/webforum");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("root123");
//
//        return dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate getJdbcTemplate() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
//        return jdbcTemplate;
//    }

}
