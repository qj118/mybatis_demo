package org.demon.dao;

import org.demon.domain.User;
import org.demon.vo.UserVo;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    void saveUser(User user);

    void updateUser(User user);

    int deleteUser(Integer userId);

    User findById(Integer userId);

    List<User> findByName(String username);

    Integer count();

    List<User> findByVo(UserVo vo);
}
