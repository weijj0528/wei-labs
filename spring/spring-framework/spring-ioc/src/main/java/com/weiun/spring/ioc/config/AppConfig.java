package com.weiun.spring.ioc.config;

import com.weiun.spring.ioc.bean.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import(OtherConfig.class)
public class AppConfig {

    @Bean
    @Scope
    public PostService postService() {
        return new PostService();
    }

}
