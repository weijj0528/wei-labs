package com.weiun.spring.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author William
 * @Date 2019/2/21
 * @Description Rest
 */
@RestController
@RequestMapping("five")
public class FiveController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> map = new HashMap<>(8);
        map.put("name", "Hello World!");
        return map;
    }

}
