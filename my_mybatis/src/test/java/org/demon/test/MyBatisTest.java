package org.demon.test;


import org.demon.dao.UserDao;
import org.demon.domain.User;
import org.demon.mybatis.io.Resources;
import org.demon.mybatis.session.SqlSession;
import org.demon.mybatis.session.SqlSessionFactory;
import org.demon.mybatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void test() throws IOException {

        // 1. 读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 创建 SqlSessionFactory 的构建对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3. 使用构建对象创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        // 4. 使用 SqlSessionFactory 生产 SqlSession 对象
        SqlSession session = factory.openSession();
        // 5. 使用 SqlSession 创建 dao 接口的代理对象（动态代理）
        UserDao userDao = session.getMapper(UserDao.class);
        // 6. 使用代理对象执行数据库操作
        List<User> users = userDao.findAll();
        for(User user: users){
            System.out.println(user);
        }
        // 7. 释放资源
        session.close();
        in.close();
    }

}
