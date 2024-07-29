package com.weiun.spring.ioc.config;

import com.weiun.spring.ioc.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtherConfig {

    @Bean(initMethod = "init", destroyMethod = "finish")
    public User user() {
        return new User("William", 18);
    }

}
