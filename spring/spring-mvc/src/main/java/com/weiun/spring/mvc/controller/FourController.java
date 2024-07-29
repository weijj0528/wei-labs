package com.weiun.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author William
 * @Date 2019/2/21
 * @Description Demo
 */
@Controller
@RequestMapping("/four")
public class FourController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

}
