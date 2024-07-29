package com.weiun.spring.ioc.app;

import com.weiun.spring.ioc.bean.User;
import com.weiun.spring.ioc.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The type Application 01.
 */
public class Application02 {

    /**
     * The entry point of application.
     * 基于Java注解配置的Spring IOC容器的基本使用
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = initIoc();
        User user = context.getBean(User.class);
        System.out.println(user.getName());
        context.close();
    }

    /**
     * {@link Application01#initIoc}
     *
     * @return
     */
    private static AnnotationConfigApplicationContext initIoc() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(AppConfig.class);
        // 可多个配置类
        annotationConfigApplicationContext.refresh();
        return annotationConfigApplicationContext;
    }

}
