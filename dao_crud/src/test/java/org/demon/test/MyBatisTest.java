package org.demon.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demon.dao.UserDao;
import org.demon.dao.impl.UserDaoImpl;
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

public class MyBatisTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private UserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        userDao = new UserDaoImpl(factory);
    }

    @After
    public void destroy() throws Exception{
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> userList = userDao.findAll();
        for(User user: userList){
            System.out.println(user);
        }
    }

    @Test
    public void testSave() throws ParseException {
        User user = new User();
        user.setUsername("Demon");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("1967-01-18");
        user.setBirthday(date);
        user.setSex("Male");
        user.setAddress("Tailand");

        userDao.saveUser(user);
    }

    @Test
    public void testDelete(){
        int result = userDao.deleteUser(11);
        System.out.println(result);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(9);
        System.out.println(user);
    }

    @Test
    public void testCount(){
        int count = userDao.findTotal();
        System.out.println(count);
    }

    @Test
    public void testUpdate(){
        User user = userDao.findById(12);
        user.setAddress("Changsha");
        userDao.updateUser(user);
    }

}
