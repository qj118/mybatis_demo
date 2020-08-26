package org.demon.dao;

import org.demon.domain.User;

import java.util.List;

public interface UserDao {

    /**
     * 返回所有用户信息，包括她名下的所有账户信息
     * @return
     */
    List<User> findAll();

}
