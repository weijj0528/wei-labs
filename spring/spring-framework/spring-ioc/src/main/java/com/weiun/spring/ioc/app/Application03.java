package com.weiun.spring.ioc.app;

import com.weiun.spring.ioc.bean.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The type Application 01.
 */
public class Application03 {

    /**
     * The entry point of application.
     * 基于Java注解配置的Spring IOC容器的基本使用
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = initIoc();
        User user = context.getBean(User.class);
        System.out.println(user.getName());
        context.close();
    }

    /**
     * {@link Application01#initIoc}
     *
     * @return
     */
    private static ClassPathXmlApplicationContext initIoc() {
        return new ClassPathXmlApplicationContext("classpath:/applicationContext03.xml");
    }

}
