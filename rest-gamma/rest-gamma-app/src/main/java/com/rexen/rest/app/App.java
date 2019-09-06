package com.rexen.rest.app;

import cn.hutool.core.io.FileUtil;
import org.apache.log4j.Logger;
import org.flow.config.ApplicationConfiguration;
import org.flow.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 程序入口
 *
 * @author gavin
 */
@Import({
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class
})
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.rexen","org.flow"},exclude = {SecurityAutoConfiguration.class})
public class App extends SpringBootServletInitializer {

    static Logger logger = Logger.getLogger(App.class);
    /**
     * 程序入口
     *
     * @param args 启动参数
     * @throws Exception 启动时异常
     */
    public static void main(String[] args) throws Exception {
        try {
            int processId = getProcessID();
            logger.info("Current process id is:" + processId);
            FileUtil.writeUtf8String(String.valueOf(processId), System.getProperty("user.dir").concat("/pid.dat"));
        } catch (Exception e) {
            logger.warn("Get process id exception:", e);
        }
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    }

    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        logger.info("Process classpath:" + System.getProperty("java.class.path"));
        logger.info("Process start dir:" + System.getProperty("user.dir"));
        logger.info(runtimeMXBean.getName());
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0]);
    }
}
