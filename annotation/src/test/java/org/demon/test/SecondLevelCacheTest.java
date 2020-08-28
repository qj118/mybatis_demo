package org.demon.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demon.dao.UserDao;
import org.demon.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class SecondLevelCacheTest {

    InputStream in;
    SqlSessionFactory factory;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("MybatisConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
    }

    @After
    public void destroy() throws Exception{
        in.close();
    }

    @Test
    public void testSecondLevelCache(){
        SqlSession session1 = factory.openSession();
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user1 = userDao1.findById(1);
        System.out.println(user1);
        session1.close();

        SqlSession session2 = factory.openSession();
        UserDao userDao2 = session2.getMapper(UserDao.class);
        User user2 = userDao2.findById(1);
        System.out.println(user2);
        session2.close();
    }
}
