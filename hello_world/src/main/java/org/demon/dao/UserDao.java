package org.demon.dao;

import org.demon.domain.User;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();
}
