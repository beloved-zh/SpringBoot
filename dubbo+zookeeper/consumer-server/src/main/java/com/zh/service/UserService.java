package com.zh.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service //注册到ioc容器
public class UserService {

    //想要拿到provider-server中的值，要去注册中心获取服务
    //远程引用指定的服务，他会按照全类名进行匹配，看谁给注册中心注册了这个全类名
    @Reference //引用  Pom坐标，可以定义相同路径的接口名
    TicketService ticketService;

    public void getTicket(){
        String ticket = ticketService.getTicket();

        System.out.println("在注册中心拿到"+ticket);
    }

}
