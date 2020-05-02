package com.zh.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Controller;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2    //开启Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A")
                ;
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("B")
                ;
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("C")
                ;
    }

    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger的环境
        Profiles profiles = Profiles.of("dev","test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Beloved")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zh.controller"))
                .build()
                ;
    }

    //配置Swagger信息==apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("Beloved","beloved.ink","1425279634@qq.com");
        return new ApiInfo(
                "Beloved的SwaggerAPI文档",
                "描述信息",
                "v1.0", //版本信息
                "beloved.ink",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

}
