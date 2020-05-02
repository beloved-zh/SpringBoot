package com.zh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/findAll")
    public List<Map<String,Object>> findAll(){
        String sql = "select * from user";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        return list;
    }

    @GetMapping("/add")
    public String add(){
        String sql = "insert into mybatis.user(id, name, pwd) values (5,'admin','123456')";

        jdbcTemplate.update(sql);

        return "添加成功";
    }

    @GetMapping("/update/{id}")
    public String add(@PathVariable("id") Integer id){
        String sql = "update mybatis.user set name=?,pwd=? where id=?";

        Object[] objects = new Object[3];
        objects[0] = "张恒";
        objects[1] = "admin";
        objects[2] = id;

        jdbcTemplate.update(sql,objects);

        return "修改成功";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        String sql = "delete from mybatis.user where id = ?";

        jdbcTemplate.update(sql,id);

        return "删除成功";
    }

}
