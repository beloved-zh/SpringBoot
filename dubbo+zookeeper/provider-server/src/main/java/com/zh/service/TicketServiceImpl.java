package com.zh.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service    //使用dubbo的@service注解，将服务发布出去
@Component  //注册到ioc容器中
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "《Java入门到放弃》";
    }
}
