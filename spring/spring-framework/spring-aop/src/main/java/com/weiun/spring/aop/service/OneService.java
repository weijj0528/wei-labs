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
public class OneService {

    public Map<String, String> aop() {
        System.out.println("One aop:start");
        Map<String, String> map = new HashMap<>();
        map.put("name", "test");
        System.out.println("One aop:map-" + map.toString());
        return map;
    }

}
