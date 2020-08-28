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
import java.util.List;

public class ResultTest {

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
        session.close();
        in.close();
    }

    @Test
    public void testSelect(){
        List<Book> books = bookDao.findAll();
        for (Book book : books) {
            System.out.println(book);
        }

        Book b = bookDao.findById(6);
        System.out.println(b);
    }
}
