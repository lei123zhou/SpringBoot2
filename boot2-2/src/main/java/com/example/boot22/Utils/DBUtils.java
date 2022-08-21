package com.example.boot22.Utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private static DruidDataSource dds;

    static {
        dds = new DruidDataSource();
        dds.setUrl("jdbc:mysql://localhost:3306/bootdb?" +
                "characterEncoding=utf8&" +
                "serverTimezone=Asia/Shanghai");
        dds.setUsername("root");
        dds.setPassword("520927zl");
        dds.setInitialSize(3);
        dds.setMaxActive(5);
    }

    public Connection getCon() throws SQLException {
        Connection conn = dds.getConnection();
        return conn;
    }
}
