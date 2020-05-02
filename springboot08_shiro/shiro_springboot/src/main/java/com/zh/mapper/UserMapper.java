package com.zh.mapper;

import com.zh.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> findAll();

    User findById(int id);

    User findByName(String name);

    int add(User user);

    int delete(int id);

    int update(User user);

    User login(String name,String pwd);

}
