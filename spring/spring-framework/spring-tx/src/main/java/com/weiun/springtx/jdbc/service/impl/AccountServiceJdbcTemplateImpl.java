package com.weiun.springtx.jdbc.service.impl;

import com.weiun.springtx.jdbc.service.AccountService;
import com.weiun.springtx.jdbc.entity.Account;
import com.weiun.springtx.jdbc.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 使用Spring集成的ORM
 * JDBC连接需要交给Spring管理
 * 才能使Spring声明式事务生效
 * 如：
 * JdbcTemplate
 * Hibernate
 * MyBatis
 */
@Service("accountServiceJdbcTemplateImpl")
public class AccountServiceJdbcTemplateImpl implements AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 注意该Mapper不是MyBatis Mapper接口
     * 而是实现的RowMapper
     * 将 ResultSet行记录转为Account
     */
    @Autowired
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transferSuccess(Integer fromUserId, Integer toUserId, Integer money) throws Exception {
        // 扣款
        minusFromUser(fromUserId, money);
        // 存款
        addToUser(toUserId, money);
    }

    private void addToUser(Integer toUserId, Integer money) throws Exception {
        String sql = "select * from t_account where id = ?";
        List<Account> list = jdbcTemplate.query(sql, new Object[]{toUserId}, accountMapper);
        if (list.isEmpty()) {
            throw new Exception("账户不存在！");
        }
        sql = "update t_account set money = money + ? where id = ?";
        int update = jdbcTemplate.update(sql, money, toUserId);
        if (update != 1) {
            throw new Exception("扣款失败！");
        }
    }

    private void minusFromUser(Integer fromUserId, Integer money) throws Exception {
        String sql = "select * from t_account where id = ?";
        List<Account> list = jdbcTemplate.query(sql, new Object[]{fromUserId}, accountMapper);
        if (list.isEmpty()) {
            throw new Exception("账户不存在！");
        }
        if (list.size() > 1) {
            throw new Exception("账户不确定！");
        }
        Account account = list.get(0);
        int nowMoney = account.getMoney();
        if (money > nowMoney) {
            throw new Exception("账户余额不足！");
        }
        sql = "update t_account set money = money - ? where id = ?";
        int update = jdbcTemplate.update(sql, money, fromUserId);
        if (update != 1) {
            throw new Exception("扣款失败！");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transferError(Integer fromUserId, Integer toUserId, Integer money) throws Exception {
        // 扣款
        minusFromUser(fromUserId, money);
        // 触发异常
        int i = 1 / 0;
        // 存款
        addToUser(toUserId, money);
    }
}
