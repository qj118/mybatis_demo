package org.demon.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demon.dao.UserDao;
import org.demon.domain.User;
import org.demon.vo.UserVo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("MybatisConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.close();
        in.close();
    }

    @Test
    public void testFindByCondtion(){
        User user = new User();

        user.setUsername("i");
        user.setAddress("i");

        List<User> users = userDao.findByCondition(user);
        for(User res : users){
            System.out.println(res);
        }
    }

    @Test
    public void testFindByInIds(){
        UserVo vo = new UserVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(4);
        ids.add(6);
        ids.add(9);
        vo.setIds(ids);

        List<User> users = userDao.findByInIds(vo);
        for(User user: users){
            System.out.println(user);
        }
    }
}
