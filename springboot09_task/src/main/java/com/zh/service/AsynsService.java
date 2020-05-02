package com.zh.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsynsService {

    //告诉spring这时一个异步任务
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);//停止三秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理.....");
    }

}
