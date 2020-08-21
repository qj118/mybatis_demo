package org.demon.mybatis.session.defaults;

import org.demon.mybatis.config.Configuration;
import org.demon.mybatis.session.SqlSession;
import org.demon.mybatis.session.SqlSessionFactory;

/**
 * SqlSessionFactory 的默认实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建一个操作数据库对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
