package com.zh.controller;

import com.zh.service.AsynsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsynsController {

    @Autowired
    private AsynsService service;


    @RequestMapping("/hello")
    public String hello(){
        service.hello();//停止三秒
        return "ok";
    }

}
