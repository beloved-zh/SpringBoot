package com.zh.controller;

import com.zh.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {

    @RequestMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "没有权限访问此页面";
    }

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping({"toLogin"})
    public String toLogin(){
        return "views/login";
    }

    @RequestMapping({"level1/{id}"})
    public String level1(@PathVariable("id") Integer id){
        return "views/level1/"+id;
    }

    @RequestMapping({"level2/{id}"})
    public String level2(@PathVariable("id") Integer id){
        return "views/level2/"+id;
    }

    @RequestMapping({"level3/{id}"})
    public String level3(@PathVariable("id") Integer id){
        return "views/level3/"+id;
    }

    @RequestMapping("/login")
    public String login(String user,String pwd,boolean rememberMe,Model model){
        System.out.println("记住我"+rememberMe);
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(user, pwd,rememberMe);
        try {
            //执行登录方法，没有异常ok
            subject.login(token);
            //成功信息存入session
//            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            User userInfo = (User) subject.getPrincipal();
            session.setAttribute("user",userInfo);
            return "index";
        }catch (LockedAccountException lae){
            model.addAttribute("msg","用户名或密码错误");
            return "views/login";
        }catch (UnknownAccountException e){ //用户名不存在
            model.addAttribute("msg","用户名错误");
            return "views/login";
        }catch (IncorrectCredentialsException e){ //密码不存在
            model.addAttribute("msg","密码错误");
            return "views/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }
}
