package com.github.weijj0528.java5.jdbc;

import org.apache.ibatis.session.TransactionIsolationLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC 事务测试
 * 3.1 事务演示 {@link #transactionTest()}
 * 3.2 读未提交演示 {@link #readUncommittedTest()} 注需要在 Mysql或其他 环境测试 hsql 暂不支持多事务处理
 * 3.3 读提交演示 {@link #readCommittedTest()} 注需要在 Mysql或其他 环境测试 hsql 暂不支持多事务处理
 * 3.4 可重复读演示 {@link #repeatableReadTest()} 注需要在 Mysql或其他 环境测试 hsql 暂不支持多事务处理
 * 3.5 序列化演示 {@link #serializableTest()} 注需要在 Mysql或其他 环境测试 hsql 暂不支持多事务处理
 *
 * @author Administrator
 * @createTime 2019/6/29 20:39
 * @description JDBC测试
 */
public class JdbcTest004 extends JdbcTest001 {

    /**
     * 事务演示
     * 修改两记录
     * 1、取消自动提交{@link Connection#setAutoCommit(boolean)}
     * 2、正常处理{@link Connection#commit()}
     * 3、出错{@link Connection#rollback()}
     *
     * @throws SQLException
     */
    public void transactionTest() throws SQLException {
        try {
            connection.setAutoCommit(false);

            String sql = "update subject set name = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            // 第一条记录
            ps.setString(1, "AAA");
            ps.setInt(2, 1);
            int i = ps.executeUpdate();
            System.out.println("--------------------------");
            statementQuery();
            // 第二条记录
            ps.setString(1, "BBB");
            // 演示成功与失败情况
            ps.setInt(2, 4);
            i = ps.executeUpdate();
            if (i <= 0) {
                // 未修改数据抛出异常触发回滚
                throw new RuntimeException("error");
            }
            System.out.println("--------------------------");
            statementQuery();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        System.out.println("--------------------------");
        statementQuery();
    }

    /**
     * 读未提交
     * 可以读取其他事务还未提交的数据，会导致 脏读 现象
     * 脏读：可以查询到其他事务还未提交的数据，如其他事务回滚，那读取到的数据应是无效数据
     *
     * @throws SQLException
     */
    public void readUncommittedTest() throws SQLException {
        // 设置隔离级别为读未提交
        connection.setTransactionIsolation(TransactionIsolationLevel.READ_UNCOMMITTED.getLevel());
        transactionIsolation();
    }

    /**
     * 读提交
     * 可以读取其他事务提交后的数据，会导致 幻读 现象
     * 幻读：查询数据后其他事务修改了数据，再次查询时数据不一致
     *
     * @throws SQLException
     */
    public void readCommittedTest() throws SQLException {
        // 设置隔离级别为读提交
        connection.setTransactionIsolation(TransactionIsolationLevel.READ_COMMITTED.getLevel());
        transactionIsolation();
    }

    /**
     * 可重复读
     * 不可以读取其他事务的数据，任何时间查询的数据均一致（事务中的修改不考虑）
     *
     * @throws SQLException
     */
    public void repeatableReadTest() throws SQLException {
        // 设置隔离级别为可重复读
        connection.setTransactionIsolation(TransactionIsolationLevel.REPEATABLE_READ.getLevel());
        transactionIsolation();
    }

    /**
     * 序列化
     * 所有事务同步执行，最严格的带离级别，性能最差
     *
     * @throws SQLException
     */
    public void serializableTest() throws SQLException {
        // 设置隔离级别为可重复读
        connection.setTransactionIsolation(TransactionIsolationLevel.SERIALIZABLE.getLevel());
        transactionIsolation();
    }

    private void transactionIsolation() throws SQLException {
        System.out.println("最初始数据---------------------------");
        statementQuery();
        // 事务2修改数据
        Connection connection2 = JdbcUtils.getConnection(url, user, password);
        connection2.setAutoCommit(false);
        String sql = "update subject set name = 'ABC' where id = 1";
        Statement statement = connection2.createStatement();
        int i = statement.executeUpdate(sql);
        if (i <= 0) {
            throw new RuntimeException("Error");
        }
        System.out.println("事务2修改后还未提交---------------------------");
        statementQuery();
        System.out.println("事务2修改后已提交---------------------------");
        // 提交
        connection2.commit();
        statementQuery();
    }

}
