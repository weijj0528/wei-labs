package com.github.weijj0528.java5.jdbc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.sql.*;

/**
 * JDBC测试
 * 驱动的加载与连接
 * 0.1 驱动加载与连接 {@link #before()}
 *
 * @author Administrator
 * @createTime 2019/6/29 20:39
 * @description JDBC测试
 */
public class JdbcTest001 {

    /**
     * Jdbc程序中的Connection，它用于代表数据库的链接，
     * Collection是数据库编程中最重要的一个对象，
     * 客户端与数据库所有交互都是通过connection对象完成的，创建方法为：
     * {@link DriverManager#getConnection(String, String, String)}
     * 其他常用方法有：
     * {@link Connection#createStatement()} 创建向数据库发送sql的statement对象。
     * {@link Connection#prepareStatement(String)} 创建向数据库发送预编译sql的PrepareSatement对象。
     * {@link Connection#prepareCall(String)} 创建执行存储过程的callableStatement对象。
     * {@link Connection#setAutoCommit(boolean)} 设置事务是否自动提交。
     * {@link Connection#commit()} 在链接上提交事务。
     * {@link Connection#rollback()} 在此链接上回滚事务。
     */
    protected Connection connection;

    protected String url = "jdbc:hsqldb:mem:test";
    protected String user = "SA";
    protected String password = "";

    /**
     * JDBC驱动加载有两种方式
     * 1、Class.forName(driverClass);
     * 2、DriverManager.registerDriver();
     * <p>
     * 推荐使用第一种方式，查看 com.mysql.jdbc.Driver
     * 会发现其内部静态代码中使用 DriverManager.registerDriver();进行了驱动加载
     * 如使用第2种方式在加载类是同样也会再执行一次，这样就会了加载两个驱动了
     * <p>
     * 注：现代JDBC驱动可以不用显示加载
     *
     * @throws Exception
     */
    public void before() throws Exception {
        // 构建H2内存数据库
        connection = JdbcUtils.getConnection(url, user, password);
        // 使用Mybatis ScriptRunner 执行SQl脚本初始化数据库
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(Resources.getResourceAsReader("db_init.sql"));
        System.out.println("==============================");
        System.out.println("=====  DbCreateSuccess  ======");
        System.out.println("==============================");
    }

    /**
     * 连接使用后需要关闭
     *
     * @throws SQLException
     */
    public void after() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    protected void statementQuery() throws SQLException {
        // 1.5 Statement 查询测试
        Statement statement = connection.createStatement();
        String sql = "Select * from subject";
        ResultSet resultSet = statement.executeQuery(sql);
        readResultSet(resultSet);
    }

    protected void readResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder sb;
        // 取结果
        while (resultSet.next()) {
            sb = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                sb.append(resultSet.getString(i));
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }

}
