package com.zh.service.impl;

import com.zh.mapper.UserMapper;
import com.zh.pojo.User;
import com.zh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }

    @Override
    public User findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public User findByName(String name) {
        return mapper.findByName(name);
    }

    @Override
    public int add(User user) {
        return mapper.add(user);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public int update(User user) {
        return mapper.update(user);
    }

    @Override
    public User login(String name, String pwd) {
        return mapper.login(name, pwd);
    }
}
