package com.rexen.rest.app.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"org.flowable.ui.modeler.service"},sqlSessionFactoryRef = "sqlSessionFactory2")
public class FlowableMybatisConfig {
    @Autowired
    @Qualifier("dataSource")
    private DataSource ds;

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager master2TransactionManager() {
        return new DataSourceTransactionManager(ds);
    }

    @Bean(value = "sqlSessionFactory2")
    @Primary
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws IOException {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(ds);
        mybatisPlus.setVfs(SpringBootVFS.class);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setCacheEnabled(false);
        configuration.setLazyLoadingEnabled(false);
        configuration.setMultipleResultSetsEnabled(true);
        configuration.setUseColumnLabel(true);
        configuration.setDefaultStatementTimeout(25000);
        configuration.setDefaultExecutorType(ExecutorType.REUSE);
        configuration.setCallSettersOnNulls(true);
        mybatisPlus.setConfiguration(configuration);
        mybatisPlus.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/META-INF/modeler-mybatis-mappings/*.xml"));
        Properties sqlSessionFactoryProperties = new Properties();
        sqlSessionFactoryProperties.setProperty("blobType","BLOB");
        sqlSessionFactoryProperties.setProperty("boolValue","TRUE");
        sqlSessionFactoryProperties.setProperty("prefix","");
        mybatisPlus.setConfigurationProperties(sqlSessionFactoryProperties);
        return mybatisPlus;
    }
}
