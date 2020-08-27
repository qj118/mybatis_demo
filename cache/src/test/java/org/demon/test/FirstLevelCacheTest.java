package org.demon.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demon.dao.BookDao;
import org.demon.domain.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class FirstLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private BookDao bookDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("MybatisConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        bookDao = session.getMapper(BookDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFirstLevelCache(){

        Book book1 = bookDao.findById(4);
        System.out.println(book1);

        //session.clearCache();

        Book book2 = bookDao.findById(4);
        System.out.println(book2);

        System.out.println(book1 == book2); // true
    }

    @Test
    public void testOneLevelCache2(){

        Book book = bookDao.findById(4);
        book.setBookName("白夜行");
        bookDao.updateBook(book);

        Book book2 = bookDao.findById(4);
        System.out.println(book == book2);
    }
}
