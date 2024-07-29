package com.weiun.springboot.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author William
 * @Date 2019/2/27
 * @Description 普通Controller返回视图
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "index";
    }

}
