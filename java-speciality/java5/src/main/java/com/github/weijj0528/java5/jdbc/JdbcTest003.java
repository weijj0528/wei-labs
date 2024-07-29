package com.github.weijj0528.java5.jdbc;

import cn.hutool.json.JSONUtil;

import java.sql.*;

/**
 * PrepareStatement
 * 2.1 PrepareStatement 更新 {@link #prepareStatementUpdateTest()}
 * 2.2 PrepareStatement 批量插入 {@link #prepareStatementBatchInsertTest()}
 * 2.3 PrepareStatement 主建获取 {@link #prepareStatementBatchInsertTest()}
 */
public class JdbcTest003 extends JdbcTest001 {

    /**
     * PreperedStatement是Statement的了类，它的实例对象可以通过调用：
     * {@link Connection#prepareStatement(String)}
     * 它允许允许使用占位符的形式描述SQL中的参数，简化sql语句的编写
     * PreperedStatement与Statement用法基本一致
     * 不同之处在于
     * 1、PreperedStatement可以使得SQL预编译以提高效率
     * 2、PreperedStatement对于sql中的参数，允许使用占位符的形式进行替换，简化SQL编写，还起到防止SQl注入问题
     * 3、批处理时只能处理一类SQL语句，而Statement可以发送任意SQL语句
     * 4、SQL注入问题演示 {@link #sqlInjectionTest()}
     *
     * @throws SQLException
     */
    public void prepareStatementUpdateTest() throws SQLException {
        // 2.1 prepareStatement 查询测试
        // PrepareStatement 用法与Statement基本差不多
        // 不同点在于可以起到预编译作用，使用?做为占位符，防止SQL注入
        String sql = "update subject set name = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "123");
        ps.setInt(2, 1);
        int i = ps.executeUpdate();
        System.out.println(i);
        statementQuery();
    }

    /**
     * 注意，不再添加整条的SQL语句，只设置对应参数即可
     *
     * @throws SQLException
     */
    public void prepareStatementBatchInsertTest() throws SQLException {
        // 2.2 prepareStatement 批量插入测试
        String sql = "INSERT INTO subject VALUES (?, ?, 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 4; i <= 10; i++) {
            ps.setInt(1, i);
            ps.setString(2, "AAA" + i);
            ps.addBatch();
        }
        int[] ints = ps.executeBatch();
        System.out.println(JSONUtil.toJsonStr(ints));
        statementQuery();
    }

    public void getGeneratedKeysTest() throws SQLException {
        // 2.3 主键获取
        String sql = "INSERT INTO subject(name,age,height,weight,active,dt) VALUES ('BBB', 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = ps.executeUpdate();
        System.out.println(i);
        ResultSet generatedKeys = ps.getGeneratedKeys();
        ResultSetMetaData metaData = generatedKeys.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println(columnCount);
        while (generatedKeys.next()) {
            String string = generatedKeys.getString(1);
            System.out.println(string);
        }
        statementQuery();
    }

}
