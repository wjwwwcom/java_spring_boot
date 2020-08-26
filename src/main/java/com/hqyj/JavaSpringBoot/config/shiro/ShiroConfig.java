package com.hqyj.JavaSpringBoot.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Component
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        manager.setRememberMeManager(rememberMeManager());
        manager.setSessionManager(sessionManager());
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());

        shiroFilter.setLoginUrl("/account/login");
        shiroFilter.setSuccessUrl("/common/dashboard");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/static/**", "anon");
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/plugin/**", "anon");
        map.put("/api/login", "anon");
        map.put("/account/register", "anon");
        map.put("/api/user", "anon");
        map.put("/common/**", "user");
        map.put("/test/**", "user");
        map.put("/**", "authc");

        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }

    /**
     * 注册shiro方言，让thymeleaf支持shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 自动代理类，支持Shiro的注解
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


    //设置cookie
    @Bean
    public SimpleCookie rememberMeCookie(){
        //设置参数名称rememberMe,对应前端的CheckBox的name=rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //httponly设置为true，客户端不会暴露脚本代码
        //使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        //cookie生效时间，单位为秒
        simpleCookie.setMaxAge(7*24*60*60);
        return simpleCookie;
    }

    //cookie管理器
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        byte[] cipherkey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
        cookieRememberMeManager.setCipherService(new AesCipherService());
        return cookieRememberMeManager;
    }

    /**
     * sessionCookie
     */
    @Bean
    public SimpleCookie sessionCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("shiro.session");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(1 * 24 * 60 * 60);
        return simpleCookie;
    }

    /**
     * 1、session 管理，去掉重定向后Url追加SESSIONID
     * 2、shiro默认Cookie名称是JSESSIONID，与servlet(jetty, tomcat等默认JSESSIONID)冲突，
     * --我们需要为shiro指定一个不同名称的Session id，否则抛出UnknownSessionException:
     * There is no session with id异常
     * 当我们使用热部署重启应用时，已有的Session消失，shiro跳转到登录页面，也会抛出此异常
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookie(sessionCookie());
        return sessionManager;
    }


}
