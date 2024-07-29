package com.github.weijj0528.java5.jdbc;

import cn.hutool.core.util.StrUtil;

import java.sql.*;

/**
 * JDBC Statement 测试
 * 0.1 驱动加载与连接 {@link #before()}
 * 1.1 Statement 插入 {@link #statementInsertTest()}
 * 1.2 Statement 删除 {@link #statementDelTest()} ()}
 * 1.3 Statement 更新 {@link #statementUpdateTest()}
 * 1.4 Statement 查询 {@link #statementBatchTest()}
 * 1.5 Statement 查询 {@link #statementQueryTest()}
 * 1.6 Statement 元数据 {@link #metaDataTest()}
 * 1.6 Statement SQL注入演示 {@link #sqlInjectionTest()}
 *
 * @author Administrator
 * @createTime 2019/6/29 20:39
 * @description JDBC测试
 */
public class JdbcTest002 extends JdbcTest001 {

    /**
     * 1.1 插入演示
     * Jdbc程序中的Statement对象用于向数据库发送SQL语句，创建方法为：
     * {@link Connection#createStatement()}
     * Statement对象常用方法：
     * {@link Statement#executeQuery(String)} 用于向数据发送查询语句
     * {@link Statement#executeUpdate(String)} 用于向数据库发送insert、update或delete语句
     * {@link Statement#execute(String)} 用于向数据库发送任意sql语句
     * {@link Statement#addBatch(String)} 把多条sql语句放到一个批处理中。
     * {@link Statement#executeBatch()} 向数据库发送一批sql语句执行。
     *
     * @throws SQLException
     */
    public void statementInsertTest() throws SQLException {
        // 1.1 Statement 插入测试
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO subject VALUES (4, 'a', 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statementQueryTest();
    }

    public void statementDelTest() throws SQLException {
        // 1.2 Statement 删除测试
        Statement statement = connection.createStatement();
        String sql = "delete from subject where id = 1";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statementQueryTest();
    }

    public void statementUpdateTest() throws SQLException {
        // 1.3 Statement 更新测试
        Statement statement = connection.createStatement();
        String sql = "update subject set name = 'AAA' where id = 1";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statementQueryTest();
    }

    public void statementBatchTest() throws SQLException {
        // 1.4 Statement 批量插入
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO subject VALUES ({}, '{}', 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        for (int i = 0; i < 100; i++) {
            String format = StrUtil.format(sql, i + 4, "ABC" + i);
            System.out.println(format);
            statement.addBatch(format);
        }
        int[] ints = statement.executeBatch();
        System.out.println(ints);
        statementQueryTest();
    }

    /**
     * 查询，从结果集中取出查询的值
     * Jdbc程序中的ResultSet用于代表Sql语句的执行结果。
     * Resultset封装执行结果时，采用的类似于表格的方式，
     * ResultSet 对象维护了一个指向表格数据行的游标，初始的时候，游标在第一行之前，
     * 调用ResultSet.next() 方法，可以使游标指向具体的数据行，进行调用方法获取该行的数据。
     * 常用方法有：
     * {@link ResultSet#next()} 移动到下一行
     * {@link ResultSet#previous()} 移动到前一行
     * {@link ResultSet#absolute(int)} 移动到指定行
     * {@link ResultSet#beforeFirst()} 移动resultSet的最前面。
     * {@link ResultSet#afterLast()} 移动到resultSet的最后面。
     *
     * @throws SQLException
     */
    public void statementQueryTest() throws SQLException {
        // 1.5 Statement 查询测试
        Statement statement = connection.createStatement();
        String sql = "Select * from subject";
        ResultSet resultSet = statement.executeQuery(sql);
        readResultSet(resultSet);
    }


    public void metaDataTest() throws SQLException {
        // 1.6 Statement 元数据
        // 可以从元数据对象中获取表名，总列娄，列名，列对应数据类型等
        Statement statement = connection.createStatement();
        String sql = "Select * from subject";
        ResultSet resultSet = statement.executeQuery(sql);
        // 元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnLabel = metaData.getColumnLabel(i);
            String columnClassName = metaData.getColumnClassName(i);
            String columnTypeName = metaData.getColumnTypeName(i);
            String format = StrUtil.format("{}: {}: {}", columnLabel, columnClassName, columnTypeName);
            System.out.println(format);
        }
    }

    /**
     * 1.7 Statement  sql注入
     * 场景，查询指定ID的subject
     *
     * @throws SQLException
     */
    public void sqlInjectionTest() throws SQLException {
        Statement statement = connection.createStatement();
        String where = "1";
        String sql = "Select * from subject where id = ";
        ResultSet resultSet = statement.executeQuery(sql + where);
        readResultSet(resultSet);
        // sql注入
        System.out.println("sql注入后查询结果------------------------------");
        where = "1 or 1 = 1";
        resultSet = statement.executeQuery(sql + where);
        readResultSet(resultSet);
    }


}
