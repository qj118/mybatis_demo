package org.demon.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.demon.dao.UserDao;
import org.demon.domain.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("org.demon.dao.UserDao.findAll");
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        SqlSession session = factory.openSession();
        /**
         * 第一个参数是 mapper 配置文件中 namespace + id
         * 第二个参数要插入的实体类对象
         */
        session.insert("org.demon.dao.UserDao.saveUser", user);
        session.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        SqlSession session = factory.openSession();
        session.update("org.demon.dao.UserDao.updateUser", user);
        session.commit();
        session.close();
    }

    @Override
    public int deleteUser(Integer userId) {
        SqlSession session = factory.openSession();
        int res = session.delete("org.demon.dao.UserDao.deleteUser", userId);
        session.commit();
        session.close();
        return res;
    }

    @Override
    public User findById(Integer id) {
        SqlSession session = factory.openSession();
        User user = session.selectOne("org.demon.dao.UserDao.findById", id);
        session.close();
        return user;
    }

    @Override
    public int findTotal() {
        SqlSession session = factory.openSession();
        int count = session.selectOne("org.demon.dao.UserDao.findTotal");
        session.close();
        return count;
    }
}
