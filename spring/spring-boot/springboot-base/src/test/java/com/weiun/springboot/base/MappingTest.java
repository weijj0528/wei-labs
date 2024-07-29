package com.weiun.springboot.base;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

public class MappingTest extends SpringbootBaseApplicationTests{


    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Test
    public void test(){

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo requestMappingInfo : handlerMethods.keySet()) {
//            requestMappingInfo.getMatchingCondition()
        }
        System.out.println(handlerMethods);
    }

}
