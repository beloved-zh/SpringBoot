package com.zh.controller;

import com.zh.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ApiOperation("hello请求")
    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello Swagger";
    }

    @ApiOperation("user请求")
    @GetMapping("/user")
    public User user(User user){
        return user;
    }

    @ApiOperation("求和请求")
    @PostMapping("/add")
    public int add(@ApiParam("参数a") int a,@ApiParam("参数b") int b){
        return a+b;
    }

}
