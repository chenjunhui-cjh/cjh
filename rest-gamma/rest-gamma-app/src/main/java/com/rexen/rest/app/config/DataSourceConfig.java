package com.rexen.rest.app.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.druid.url}")
    private String url;

    @Value("${spring.datasource.druid.username}")
    private String user;

    @Value("${spring.datasource.druid.password}")
    private String password;

    @Value("${spring.datasource.druid.driver-class-name}")
    private String driverClass;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
         DruidDataSource dataSource = new DruidDataSource();
         dataSource.setDriverClassName(driverClass);
         dataSource.setUrl(url);
         dataSource.setUsername(user);
         dataSource.setPassword(password);
         return dataSource;
    }



}
