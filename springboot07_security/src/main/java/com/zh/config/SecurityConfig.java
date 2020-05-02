package com.zh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity  //开启WebSecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //链式编程

        /**
         * 首页所有人可以访问，功能页只有对应权限的人才能访问
         *
         * antMatchers：访问路径
         * permitAll：全部用户
         * hasRole：对应的权限
         */
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");


        /**
         * 没有权限，跳转默认登录页面    /login
         * 跳转自定义登录页面
         * .loginPage("自定义请求");
         * 将此处的请求写在from中的请求，登录调用Security的判断
         * 或者使用.loginProcessingUrl("请求")这里是登录请求
         * loginPage去登陆
         *
         *
         * 自定义from表单属性
         * .usernameParameter("user")
         * .passwordParameter("pwd")
         */
        http.formLogin().loginPage("/toLogin").usernameParameter("user").passwordParameter("pwd");

        /**
         * 开启注销，跳到首页
         * .deleteCookies("remove")          删除cookies
         * .invalidateHttpSession(false)     清除session
         *
         * a标签是get请求
         * 防止网站攻击
         * 解决方法：使用post，或者关闭csrf
         */
        http.csrf().disable();  //关闭csrf功能
        http.logout().logoutSuccessUrl("/");


        /**
         * 开启记住我功能
         * 保存在 cookie 默认保存两周
         * 自定义记住我
         * .rememberMeParameter("name值");
         */
        http.rememberMe().rememberMeParameter("remember");
    }


    /**
     * 认证
     * 密码编码：PasswordEncoder
     * 使用Security的登录，密码不能使用明文，应对密码进行加密
     * Spring Security 5.0+ 新增自带加密方法
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //数据正常从数据库取出
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("beloved").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");

    }
}
