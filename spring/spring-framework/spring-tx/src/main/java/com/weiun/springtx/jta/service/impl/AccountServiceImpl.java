package com.weiun.springtx.jta.service.impl;

import com.weiun.springtx.jta.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Qualifier("jdbcTemplate1")
    @Autowired
    private JdbcTemplate jdbcTemplate1;

    @Qualifier("jdbcTemplate2")
    @Autowired
    private JdbcTemplate jdbcTemplate2;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addAccountSuccess(Integer userId, Integer money) {
        // 同时向两数据库中添加一个相同的账户
        String sql = "insert into t_account values(?, ?)";
        jdbcTemplate1.update(sql, userId, money);
        jdbcTemplate2.update(sql, userId, money);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addAccountError(Integer userId, Integer money) {
        // 同时向两数据库中添加一个相同的账户
        String sql = "insert into t_account values(?, ?)";
        jdbcTemplate1.update(sql, userId, money);
        int i = 1 / 0;
        jdbcTemplate2.update(sql, userId, money);
    }
}
