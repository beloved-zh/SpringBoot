package com.zh.mapper;


import com.zh.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper : 表示本类是一个 MyBatis 的 Mapper
@Mapper
@Repository
public interface UserMapper {

    List<User> findAll();

    User findById(int id);

    int add(User user);

    int delete(int id);

    int update(User user);

}
