package com.zh.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    //cron表达式
    //秒 分 时 日 月 周几
    /*
     * 0 18 15 * * ?  每天的15点18分0秒执行一次
     */
    @Scheduled(cron = "0/1 0/1 15 * * ?")
    public void hello(){
        System.out.println("hello被执行了");
    }

}
