package com.zh.service;

import com.zh.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User findByName(String name);

    int add(User user);

    int delete(int id);

    int update(User user);

    User login(String name,String pwd);

}
