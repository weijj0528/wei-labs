package com.weiun.dubbo.consumer;

import com.weiun.dubbo.provider.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class WeiConsumerApplication {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        DemoService demoService = context.getBean(DemoService.class);
        String hello = demoService.sayHello("你好");
        System.out.println(hello);
    }
}
