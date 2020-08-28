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

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AnnotationCRUDTest {

    private InputStream in;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("MybatisConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() throws ParseException {
        User user = new User();
        user.setUsername("kikidu");
        user.setSex("Female");
        user.setAddress("Beijing");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("1980-08-08");
        user.setBirthday(date);

        userDao.saveUser(user);
    }

    @Test
    public void testDelete(){
        int res = userDao.deleteUser(13);
        System.out.println(res);
    }

    @Test
    public void testUpdate(){
        User user = userDao.findById(12);
        user.setUsername("fan");
        int res = userDao.updateUser(user);
        System.out.println(res);
    }

    @Test
    public void testSelect(){

        int c = userDao.count();
        System.out.println(c);

        List<User> users = userDao.findByName("i");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOne2Many(){
        List<User> users = userDao.findAllUserAccount();
        for (User user : users) {
            System.out.println("---------------------------------------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

}
