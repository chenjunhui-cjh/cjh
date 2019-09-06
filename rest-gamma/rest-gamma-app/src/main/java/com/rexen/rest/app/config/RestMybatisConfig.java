package com.rexen.rest.app.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.annotation.DbType;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = {"com.rexen.rest.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class RestMybatisConfig {
    @Autowired
    @Qualifier("dataSource")
    private DataSource ds;

    @Autowired(required = false)
    private org.apache.ibatis.plugin.Interceptor[] interceptors = null;

    @Bean(value = "sqlSessionFactory1")
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws IOException {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(ds);
        mybatisPlus.setTypeAliasesPackage("com.baomidou.mybatisplus.test.h2.entity");
        mybatisPlus.setVfs(SpringBootVFS.class);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        mybatisPlus.setConfiguration(configuration);
        mybatisPlus.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**/*Mapper.xml"));
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            mybatisPlus.setPlugins(this.interceptors);
        }
        // MP 全局配置，更多内容进入类看注释
        GlobalConfig.DbConfig globalConfig = new GlobalConfig.DbConfig();
        globalConfig.setDbType(DbType.MYSQL);//数据库类型
        // ID 策略 AUTO->`0`("数据库ID自增") INPUT->`1`(用户输入ID") ID_WORKER->`2`("全局唯一ID") UUID->`3`("全局唯一ID")
        globalConfig.setLogicDeleteValue("-1");
        globalConfig.setLogicNotDeleteValue("1");
        globalConfig.setIdType(IdType.ID_WORKER);
        //MP 属性下划线 转 驼峰 , 如果原生配置 mc.setMapUnderscoreToCamelCase(true) 开启，该配置可以无。
        globalConfig.setTableUnderline(true);
//        globalConfig.setKeyGenerator();
        mybatisPlus.setGlobalConfig(new GlobalConfig().setDbConfig(globalConfig));
        MybatisConfiguration mc = new MybatisConfiguration();
        // 对于完全自定义的mapper需要加此项配置，才能实现下划线转驼峰
        mc.setUseGeneratedKeys(true);
        mc.setMapUnderscoreToCamelCase(true);
//        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mybatisPlus.setConfiguration(mc);
//        if (this.databaseIdProvider != null) {
//            mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
//        }
//        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
//            mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
//        }
//        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
//            mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
//        }
//        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
//            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
//        }
        return mybatisPlus;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
//        page.setOptimizeType("aliDruid");
        return page;
    }
}
