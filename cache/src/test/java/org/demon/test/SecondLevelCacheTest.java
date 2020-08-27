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

public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;


    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("MybatisConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);

    }

    @After
    public void destroy() throws IOException {
        in.close();
    }

    @Test
    public void testSecondLevelCache(){
        SqlSession session1 = factory.openSession();
        BookDao bookDao1 = session1.getMapper(BookDao.class);
        Book book1 = bookDao1.findById(5);
        System.out.println(book1);
        session1.close();

        SqlSession session2 = factory.openSession();
        BookDao bookDao2 = session2.getMapper(BookDao.class);
        Book book2 = bookDao2.findById(5);
        System.out.println(book2);
        session2.close();

        /**
         * 所缓存的类一定要实现 java.is.Serializable 接口
         * 因为二级缓存中保存的数据本身，本次调出缓存都会根据缓存的数据重新创建对象。
         */
        System.out.println(book1 == book2); // false
    }
}
