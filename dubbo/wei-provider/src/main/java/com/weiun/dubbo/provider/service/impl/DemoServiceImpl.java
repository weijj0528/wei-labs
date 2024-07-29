package com.weiun.dubbo.provider.service.impl;

import com.weiun.dubbo.provider.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    public String sayHello(String content) {
        System.out.println(content);
        return content;
    }

}
