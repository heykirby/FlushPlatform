package com.pku.edu.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pku.edu.backend.model.User;


@Mapper
public interface UserMapper {
    @Select("select * from users")
    List<User> selectAll();
    @Select("select * from users where id = #{id}")
    User selectById(@Param("id") Long id);
    @Insert("insert into users(username, password, email, phone, status) values (#{username}, #{password}, #{email}, #{phone}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE users SET username = #{username}, password = #{password}, email = #{email}, phone = #{phone}, status = #{status} WHERE id = #{id}")
    int update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    int delete(@Param("id") Long id);

}
