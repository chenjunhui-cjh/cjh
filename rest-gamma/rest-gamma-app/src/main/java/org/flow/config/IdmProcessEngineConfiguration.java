package org.flow.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.idm.spring.authentication.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class IdmProcessEngineConfiguration extends SpringIdmEngineConfiguration {

    @Bean
    protected ProcessEngine engine() {
        //创建流程引擎  服务启动是初始化一次即可
        return ProcessEngines.getDefaultProcessEngine();
    }

    @Bean
    public PasswordEncoder bCryptEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringEncoder passwordEncoder(){
        return new SpringEncoder(bCryptEncoder());
    }

    @Override
    public IdmEngineConfiguration setPasswordEncoder(org.flowable.idm.api.PasswordEncoder passwordEncoder) {
        return super.setPasswordEncoder(passwordEncoder());
    }
}
