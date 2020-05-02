package com.zh.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean        3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //关联DefaultWebSecurityManager
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        /**
         * 添加shiro的内置过滤器
         *
         * anon：无需认证就可以访问
         * authc：必须认证才可以访问
         * user：必须拥有 记住我 功能才能访问
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        //拦截请求
        Map<String,String> filterMap = new HashMap<>();
        filterMap.put("/level1/*","anon");
        filterMap.put("/level2/*","authc,perms[vip1]");
        filterMap.put("/level3/*","authc,perms[vip2]");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求
        bean.setLoginUrl("/toLogin");

        //未授权页面
        bean.setUnauthorizedUrl("/unauthorized");

        return bean;
    }


    //DefaultWebSecurityManager     2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联UserRealm
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }


    //创建realm对象，需要自定义       1
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //配置ShiroDialect：用来整合Shiro和thymeleaf
      @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


    /**
     * cookie对象
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：

        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
}
