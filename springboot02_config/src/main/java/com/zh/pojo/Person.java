package com.zh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * 通过这个配置产生提示，如果不设置，就会提示爆红  prefix绑定配置文件的值
 *
 * @ConfigurationProperties作用：
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * 告诉SpringBoot将本类中的所有属性和配置问价中相关的配置进行绑定
 * 参数prefix = "person"：将配置文件中的person与下面的属性一一对应
 *
 * 只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
 */
@ConfigurationProperties(prefix = "person")
@Validated//数据校验
public class Person {

    private String name;
    private Integer age;
    @Email(message = "邮箱格式错误")
    private String email;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

}
