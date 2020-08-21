package org.demon.dao;

import org.demon.domain.User;
import org.demon.mybatis.annotations.Select;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from t_user")
    List<User> findAll();
}
