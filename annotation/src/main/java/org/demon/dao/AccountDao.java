package org.demon.dao;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.demon.domain.Account;

import java.util.List;

public interface AccountDao {

    @Select("select * from t_user_account")
    @Results(id = "accountMap",
             value = {
                @Result(id = true, column = "id", property = "id"),
                @Result(column = "uid", property = "uid"),
                @Result(column = "balance", property = "balance"),
                     /**
                      * column 是 @One 注解中 select 指定方法所需要的参数，
                      * @One 中 select 用来指定取出对应 User 数据的方法的全类名；
                      * @One 中 fetchType 用来指定是懒加载还是立即加载。
                       */
                @Result(column = "uid", property = "user",
                        one = @One(select = "org.demon.dao.UserDao.findById",
                                   fetchType = FetchType.LAZY))
             })
    List<Account> findAll();

    @Select("select * from t_user_account where uid = #{uid};")
    List<Account> findByUid(int uid);
}
