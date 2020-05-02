package com.zh.controller;

import com.zh.mapper.UserMapper;
import com.zh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControlle {

    @Autowired
    private UserMapper mapper;

    @GetMapping("/findAll")
    public List<User> findAll(){

        List<User> list = mapper.findAll();

        return list;
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){

        User user = mapper.findById(id);

        return user;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){

        mapper.delete(id);

        return "删除成功";
    }

    @GetMapping("/add")
    public String add(){

        User user = new User(5,"哈哈","888888");

        mapper.add(user);

        return "添加成功";
    }

    @GetMapping("/update")
    public String update(){

        User user = new User(5,"10086","00000");

        mapper.update(user);

        return "修改成功";
    }
}
