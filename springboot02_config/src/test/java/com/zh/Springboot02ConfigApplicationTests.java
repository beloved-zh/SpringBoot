package com.zh;

import com.zh.pojo.Dog;
import com.zh.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
    private Person person;
    @Autowired
    private Dog dog;

    @Test
    void contextLoads() {
        System.out.println(person);
        System.out.println(dog);
    }

}
