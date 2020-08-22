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

public class CrudTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void  init() throws IOException {
        in = Resources.getResourceAsStream("SqlConfig.xml");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);

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
    public void testFindAll() {

        List<User> users = userDao.findAll();
        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void testSave() throws ParseException {

        User user = new User();
        user.setUsername("Ella");
        user.setSex("female");
        user.setAddress("中国台湾");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = "1981-06-18";
        Date date = formatter.parse(s);
        user.setBirthday(date);
        System.out.println(user);
        userDao.saveUser(user);
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        // 先查后改
        User user = userDao.findById(1);
        user.setAddress("洛阳");
        userDao.updateUser(user);
    }

    @Test
    public void testDelete(){
        int res = userDao.deleteUser(10);
        System.out.println(res);
    }

    @Test
    public void testQuery(){
        List<User> users = userDao.findByName("%a%");
        for(User user : users){
            System.out.println(user);
        }
        System.out.println("一共有 " + userDao.count() + " 个用户。");
    }

}
