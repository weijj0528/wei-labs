package com.weiun.springtx.jdbc.service.impl;

import com.weiun.springtx.jdbc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 直接使用JDBC连接操作数据库
 * Spring声明式事务不会生效
 *
 */
@Service("accountServiceJdbcImpl")
public class AccountServiceJdbcImpl implements AccountService {

    @Autowired
    private DataSource dataSource;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transferSuccess(Integer fromUserId, Integer toUserId, Integer money) throws Exception {
        Connection connection = dataSource.getConnection();
        // 扣款
        minusFromUser(connection, fromUserId, money);
        // 存款
        addToUser(connection, toUserId, money);
    }

    private void addToUser(Connection connection, Integer toUserId, Integer money) throws Exception {
        String sql = "select * from t_account where id = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        preparedStatement1.setInt(1, toUserId);
        ResultSet resultSet = preparedStatement1.executeQuery();
        if (!resultSet.next()) {
            throw new Exception("账户不存在！");
        }
        sql = "update t_account set money = money + ? where id = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        preparedStatement2.setInt(1, money);
        preparedStatement2.setInt(2, toUserId);
        int update = preparedStatement2.executeUpdate();
        if (update != 1) {
            throw new Exception("扣款失败！");
        }
    }

    private void minusFromUser(Connection connection, Integer fromUserId, Integer money) throws Exception {
        String sql = "select * from t_account where id = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        preparedStatement1.setInt(1, fromUserId);
        ResultSet resultSet = preparedStatement1.executeQuery();
        if (!resultSet.next()) {
            throw new Exception("账户不存在！");
        }
        int nowMoney = resultSet.getInt("money");
        if (money > nowMoney) {
            throw new Exception("账户余额不足！");
        }
        sql = "update t_account set money = money - ? where id = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        preparedStatement2.setInt(1, money);
        preparedStatement2.setInt(2, fromUserId);
        int update = preparedStatement2.executeUpdate();
        if (update != 1) {
            throw new Exception("扣款失败！");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transferError(Integer fromUserId, Integer toUserId, Integer money) throws Exception {
        Connection connection = dataSource.getConnection();
        // 扣款
        minusFromUser(connection, fromUserId, money);
        // 触发异常
        int i = 1 / 0;
        // 存款
        addToUser(connection, toUserId, money);
    }
}
