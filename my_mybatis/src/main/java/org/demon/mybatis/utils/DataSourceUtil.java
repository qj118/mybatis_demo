package org.demon.mybatis.utils;

import org.demon.mybatis.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 用来创建数据连接的工具类
 */
public class DataSourceUtil {

    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
