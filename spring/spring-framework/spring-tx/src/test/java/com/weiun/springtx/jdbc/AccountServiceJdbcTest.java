package com.weiun.springtx.jdbc;

import com.weiun.springtx.jdbc.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountServiceJdbcTest {

    private AccountService accountService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-jdbc.xml");
        accountService = (AccountService) context.getBean("accountServiceJdbcImpl");
    }

    @Test
    public void transferSuccess() throws Exception {
        accountService.transferSuccess(1, 0, 10);
    }

    @Test
    public void transferError() throws Exception {
        accountService.transferError(1, 0, 10);
    }
}