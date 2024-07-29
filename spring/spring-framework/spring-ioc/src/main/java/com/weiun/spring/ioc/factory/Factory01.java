package com.weiun.spring.ioc.factory;

import com.weiun.spring.ioc.bean.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * The type Factory 01.
 */
public class Factory01 {

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("factory.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        User user = factory.getBean(User.class);
        System.out.println(user);
    }

}
