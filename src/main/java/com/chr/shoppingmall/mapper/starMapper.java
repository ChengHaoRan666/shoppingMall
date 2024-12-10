package com.chr.shoppingmall.mapper;


import com.chr.shoppingmall.entity.star;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 程浩然
 * @Create: 2024/9/16 - 11:35
 * @Description: 收藏表
 */
@Component
public interface starMapper {
    /**
     * 增
     */
    @Insert("insert into stars values (null, #{userID}, #{productID}, #{quantity});")
    int addStar(star star);

    /**
     * 删
     */
    @Delete("delete from stars where StarID = #{StarID};")
    int deleteStar(Integer StarID);

    /**
     * 根据用户id删收藏
     */
    @Delete("delete from stars where UserID = #{userId};")
    int deleteStarByUserId(Integer userId);

    /**
     * 通过用户id和商品id删
     */
    @Delete("delete from stars where UserID =#{userId} and ProductID=#{productId}")
    int deleteStarByUserIdProductId(Integer userId, Integer productId);

    /**
     * 通过商品id删（商品下架）
     */
    @Delete("delete from stars where ProductID = #{productId};")
    int deleteStarByProductId(Integer productId);

    /**
     * 改
     */
    @Update("update stars set UserID = #{star.UserID}, ProductID = #{star.productID}, Quantity = #{star.quantity} where StarID = #{StarID};")
    int updateStar(@Param("StarID") Integer starID, @Param("star") star star);

    /**
     * 查
     */
   @Select("select * from stars where UserID = #{UserID};")
    List<star> selectStar(@Param("UserID") Integer userId);

    /**
     * 获取用户收藏数量
     */
    @Select("select count(*) from stars where UserID = #{userId}")
    Integer getNumber(Integer userId);
}
