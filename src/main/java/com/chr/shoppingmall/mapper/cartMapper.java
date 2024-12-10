package com.chr.shoppingmall.mapper;


import com.chr.shoppingmall.entity.cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 程浩然
 * @Create: 2024/9/16 - 11:13
 * @Description: 购物车
 */
@Component
public interface cartMapper {
    /**
     * 增
     */
    @Insert("insert into carts values (null, #{userID}, #{productID}, #{quantity})")
    int addCart(cart cart);

    /**
     * 删
     */
    @Delete("delete from carts where CartID = #{CartID}")
    int deleteCart(Integer CartID);


    /**
     * 根据用户id删购物车
     */

    @Delete("delete from carts where UserID = #{userId}")
    int deleteCartByUserId(Integer userId);

    /**
     * 根据用户id和商品id删
     */
    @Delete("delete from carts where UserID = #{userId} and ProductID=#{ProductId}")
    int deleteCartByUserIdProductId(Integer userId, Integer ProductId);

    /**
     * 根据商品id删除购物车（下架）
     */
    @Delete("delete from carts where ProductID=#{productId}")
    int deleteCartByProductId(Integer productId);


    /**
     * 查
     */
    @Select("select * from carts where UserID = #{userId}")
    List<cart> selectCart(Integer userId);
}
