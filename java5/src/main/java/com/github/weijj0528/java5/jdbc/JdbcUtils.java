package com.github.weijj0528.java5.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Jdbc utils.
 *
 * @author Administrator
 * @createTime 2019 /6/29 20:19
 * @description JDBC工具类
 */
public class JdbcUtils {

    /**
     * Init.
     * 初始化，驱动加载
     *
     * @throws ClassNotFoundException the class not found exception
     */
    public static void init(String driverClass) throws ClassNotFoundException {
        // 注: 现在版本的JDBC不需要配置driver，因为不需要Class.forName手动加载驱动
        Class.forName(driverClass);
    }

    /**
     * The constant getConnection.
     * 获取数据库连接
     *
     * @return the connection
     */
    public static Connection getConnection(String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
