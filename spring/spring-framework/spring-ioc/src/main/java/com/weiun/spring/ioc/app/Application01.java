package com.weiun.spring.ioc.app;

import com.weiun.spring.ioc.bean.User;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The type Application 01.
 */
public class Application01 {

    /**
     * The entry point of application.
     * 基于XML配置文件的Spring IOC容器的基本使用
     * 1、容器的初始化{@link Application01#initIoc}
     * 2、Bean的获取{@link AbstractApplicationContext#getBean}
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
     * 容器的初始化
     * 1、初始化主要方法{@link AbstractApplicationContext#refresh}
     * 1.1、初始化准备{@link AbstractApplicationContext#prepareRefresh}
     * 1.2、工厂获取{@link AbstractApplicationContext#obtainFreshBeanFactory}
     * 1.3、工厂准备{@link AbstractApplicationContext#prepareBeanFactory}
     * 1.4、工厂后置处理{@link AbstractApplicationContext#postProcessBeanFactory}
     * 1.5、工厂后置处理{@link AbstractApplicationContext#invokeBeanFactoryPostProcessors}
     * 1.6、注册Bean后置处理{@link AbstractApplicationContext#registerBeanPostProcessors}
     * 1.7、初始化消息源{@link AbstractApplicationContext#initMessageSource}
     * 1.8、初始化事件处理{@link AbstractApplicationContext#initApplicationEventMulticaster}
     * 1.9、子类初始化{@link AbstractApplicationContext#onRefresh}
     * 1.10、注册监听器{@link AbstractApplicationContext#registerListeners}
     * 1.11、完成工厂初始化{@link AbstractApplicationContext#finishBeanFactoryInitialization}
     * 1.12、完成刷新{@link AbstractApplicationContext#finishRefresh}
     *
     * @return
     */
    private static ClassPathXmlApplicationContext initIoc() {
        return new ClassPathXmlApplicationContext("classpath:/applicationContext01.xml");
    }

}
