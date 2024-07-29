package com.weiun.springsecurity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello ,spring security!";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "Hello ,admin!";
    }
    @RequestMapping("/user")
    public String user() {
        return "Hello ,user!";
    }

}
