package com.chr.shoppingmall.mapper;


import com.chr.shoppingmall.entity.user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 程浩然
 * @Create: 2024/9/13 - 15:49
 * @Description: 用户表
 */
@Component
public interface userMapper {
    /**
     * 增
     */
    @Insert("insert into users values (null, #{username}, #{password}, #{email}, #{phone}, #{date}, #{lestDate});")
    int addUser( user user);

    /**
     * 删
     */
   @Delete("delete from users where UserID = #{id}")
    int deleteUser(Integer id);

    /**
     * 改
     */
    @Update("update users set Username = #{newUser.username},Password=#{newUser.password},Email=#{newUser.email},PhoneNumber=#{newUser.phone},RegistrationDate=#{newUser.date},LastLoginDate=#{newUser.lestDate} where UserID = #{id};")
    int updateUser(@Param("id") Integer id, @Param("newUser") user newUser);

    /**
     * 通过 用户名 username 查询用户信息
     */
    List<user> selectUserByUsername(@Param("username") String username);

    /**
     * 通过 id 查询单个用户信息
     *
     * @param id
     * @return
     */
    user selectUserById(@Param("id") Integer id);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<user> selectUserAll();
}
