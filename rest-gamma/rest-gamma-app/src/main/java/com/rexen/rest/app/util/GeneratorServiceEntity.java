package com.rexen.rest.app.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成工具类
 *
 * @author Leo
 */
public class GeneratorServiceEntity {

    public static void generateCode() {
        String packageName = "com.rexen.rest";
        boolean serviceNameStartWithI = false;
        // 修改替换成你需要的表名，多个表名传数组
        generateByTables(serviceNameStartWithI, packageName, "system_user");
    }

    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://172.17.2.194:3306/rest_gamma?useSSL=false";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                //设置数据库类型
                .setDbType(DbType.MYSQL)
                //数据库链接地址
                .setUrl(dbUrl)
                //数据库链接用户名
                .setUsername("root")
                //数据库链接密码
                .setPassword("Rexen123")
                //数据库链接驱动
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig
                //全局大写命名
                .setCapitalMode(true)
                //实体是否为lombok模型
                .setEntityLombokModel(false)
                //数据库列名下划线命名
                //.setDbColumnUnderline(true)
                //列名到属性名命名转换
                .setNaming(NamingStrategy.underline_to_camel)
                //包含表名
                .setInclude(tableNames);
        config.setActiveRecord(false)
                //作者，修改为自己的名字
                .setAuthor("Leo")
                //生成文件位置
                .setOutputDir("/home/code/codeGeneration")
                //XML 二级缓存
                .setEnableCache(false)
                //是否生成XML内的BaseResultMap
                .setBaseResultMap(true)
                //是否生成XML内的BaseColumnList
                .setBaseColumnList(true)
                //是否覆盖已有文件
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                //设置默认包名
                                .setParent(packageName)
                                .setController("boot.controller")
                                .setEntity("model.entity")
                                .setService("service")
                                .setMapper("mapper")
                                .setServiceImpl("service.impl")
                )
                .setTemplate(
                        new TemplateConfig().setEntity("/templates/restEntity.java")
                )
                .execute();
    }

    private static void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }

    public static void main(String[] args){
        generateCode();
    }
}
