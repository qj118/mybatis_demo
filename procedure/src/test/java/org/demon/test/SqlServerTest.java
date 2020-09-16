package org.demon.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demon.dao.StatusGroupDao;
import org.demon.domain.StatusGroup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SqlServerTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private StatusGroupDao statusDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis_config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        statusDao = session.getMapper(StatusGroupDao.class);
    }

    @After
    public void destroy() throws IOException{
        session.close();
        in.close();
    }

    @Test
    public void testConnect(){
        List<StatusGroup> groups = statusDao.group("2020-08-01", "2020-08-31");
        for (StatusGroup group : groups) {
            System.out.println(group);
        }
    }
}
