package com.zh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * templates下的页面，只能通过Controller来跳转
 * 这个需要模板引擎的支持！ Thymeleaf
 */
@Controller
public class MyController {
    @RequestMapping("/test2")
    public String test2(Model model){

        model.addAttribute("msg","<h1>Hello SpringBoot</h1>");
        //Arrays.asList将数组转换为集合
        model.addAttribute("users", Arrays.asList("张三","李四","王五"));

        return "test";
    }


    @RequestMapping("/test")
    public String test(Model model){

        model.addAttribute("msg","我是提示信息");

        return "test";
    }

}
