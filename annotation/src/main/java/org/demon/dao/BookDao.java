package org.demon.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.demon.domain.Book;

import java.util.List;

public interface BookDao {

    /**
     * @Results 用于封装结果
     *      id 给结果集一个标识
     *      value 由 @Result 组成的集合
     * @Result 指定数据库字段与实体类属性的对应关系
     *      id 布尔类型，用于标识该属性是否是主键
     *      column 数据库字段或查询语句中的别名
     *      property 实体类属性名
     */
    @Select("select * from t_book;")
    @Results(id = "bookMap",
             value = {
                @Result(id = true, column = "id", property = "bookId"),
                @Result(column = "name", property = "bookName"),
                @Result(column = "author", property = "bookAuthor")
            })
    List<Book> findAll();

    /**
     * @ResultMap 用于指定已有的结果封装
     *      value 由结果封装 id 组成的数组
     */
    @Select("select * from t_book where id = #{bId};")
    @ResultMap(value = {"bookMap"})
    Book findById(int id);
}
