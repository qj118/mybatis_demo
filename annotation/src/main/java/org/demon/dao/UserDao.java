package org.demon.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.demon.domain.User;

import java.util.List;

@CacheNamespace(blocking = true) // 基于注解方式实现配合二级缓存
public interface UserDao {

    @Insert("insert into t_user(username, birthday, sex, address) values(#{username}, #{birthday}, #{sex}, #{address});")
    int saveUser(User user);

    @Delete("delete from t_user where id = #{uid};")
    int deleteUser(int id);

    @Update("update t_user set username = #{username}, birthday = #{birthday}, sex = #{sex}, address = #{address} where id = #{id};")
    int updateUser(User user);

    @Select("select * from t_user where id = #{uid};")
    User findById(int id);

    @Select("select * from t_user;")
    List<User> findAll();

    //@Select("select * from t_user where username like #{username};")
    @Select("select * from t_user where username like '%${value}%'")
    List<User> findByName(String username);

    @Select("select count(*) from t_user;")
    int count();

    @Select("select * from t_user;")
    @Results(id = "userMap",
             value = {
                @Result(id = true, column = "id", property = "id"),
                @Result(column = "username", property = "username"),
                @Result(column = "birthday", property = "birthday"),
                @Result(column = "sex", property = "sex"),
                @Result(column = "address", property = "address"),
                @Result(column = "id", property = "accounts",
                        many = @Many(select = "org.demon.dao.AccountDao.findByUid",
                                     fetchType = FetchType.LAZY)
                )
             })
    List<User> findAllUserAccount();


}
