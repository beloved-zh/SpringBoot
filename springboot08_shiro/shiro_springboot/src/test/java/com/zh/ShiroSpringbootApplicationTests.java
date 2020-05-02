package com.zh;

import com.zh.pojo.User;
import com.zh.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    private UserService service;

    @Test
    void contextLoads() {

        System.out.println(service.delete(9));

    }

}
