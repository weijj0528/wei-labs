package com.weiun.spring.aop.controller;

import com.weiun.spring.aop.service.OneService;
import com.weiun.spring.aop.service.SecondService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AopServiceTest {

    @Autowired
    private OneService oneService;
    @Autowired
    private SecondService secondService;

    @Test
    public void oneTest() {
        oneService.aop();
    }

    @Test
    public void secondTest() {
        secondService.aop();
    }
}