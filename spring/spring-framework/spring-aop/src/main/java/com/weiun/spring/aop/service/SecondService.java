package com.weiun.spring.aop.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author William
 * @Date 2019/2/20
 * @Description
 */
@Service
public class SecondService {

    public Map<String, String> aop() {
        System.out.println("Second aop:start");
        Map<String, String> map = new HashMap<>();
        map.put("name", "test");
        System.out.println("Second aop:map-" + map.toString());
        return map;
    }

}
