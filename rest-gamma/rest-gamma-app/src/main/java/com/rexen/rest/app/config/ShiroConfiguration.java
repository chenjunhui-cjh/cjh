package com.rexen.rest.app.config;

import com.rexen.rest.app.security.RedisCacheManager;
import com.rexen.rest.app.security.RedisSessionDAO;
import com.rexen.rest.app.security.RestRealm;
import org.apache.log4j.Logger;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro配置类
 *
 * @author : GavinHacker
 * @since : Created in 下午1:35 18/3/3
 */
@Configuration
public class ShiroConfiguration {

    Logger logger = Logger.getLogger(ShiroConfiguration.class);

    @Value("${cluster}")
    private boolean clusterMode;

    @Bean
    public RestRealm restRealm() {
        RestRealm myShiroRealm = new RestRealm();
        return myShiroRealm;
    }

    @Bean
    public RedisSessionDAO getRedisSessionDao(){
        return new RedisSessionDAO();
    }

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        if(clusterMode) {
            sessionManager.setSessionDAO(getRedisSessionDao());
            logger.info("Shiro配置模式：集群");
        } else {
            logger.info("Shiro配置模式：单机");
        }
        sessionManager.setGlobalSessionTimeout(1800000L);
        sessionManager.setCacheManager(redisCacheManager());
        sessionManager.setSessionIdCookieEnabled(true);
        Cookie sessionIdCookie = sessionManager.getSessionIdCookie();
        sessionIdCookie.setPath("/");
        sessionIdCookie.setName("restcid");
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setRealm(restRealm());
        return securityManager;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>(16);
        map.put("/sysUser/login", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/webjars/*", "anon");
        map.put("/swagger-resources/*", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("swagger-resources", "anon");
        map.put("/test/**", "anon");
        map.put("/idm/**","anon");
        map.put("/app/*", "anon");
        map.put("/app/**","anon");
        map.put("/render/**","authc");
        map.put("/a/b/render/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/sysUser/shiroVerify");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}