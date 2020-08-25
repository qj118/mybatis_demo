package org.demon.dao;

import org.demon.domain.User;
import org.demon.vo.UserVo;

import java.util.List;

public interface UserDao {

    List<User> findByCondition(User user);

    List<User> findByInIds(UserVo vo);
}
