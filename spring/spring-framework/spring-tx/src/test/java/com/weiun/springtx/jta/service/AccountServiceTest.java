package com.weiun.springtx.jta.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountServiceTest {

    private AccountService accountService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-jta.xml");
        accountService = context.getBean(AccountService.class);
    }

    @Test
    public void addAccountSuccess() {
        accountService.addAccountSuccess(6, 0);
    }

    @Test
    public void addAccountError() {
        accountService.addAccountError(3, 0);
    }
}