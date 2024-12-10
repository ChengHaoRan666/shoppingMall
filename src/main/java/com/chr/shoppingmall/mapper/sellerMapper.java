package com.chr.shoppingmall.mapper;


import com.chr.shoppingmall.entity.seller;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 程浩然
 * @Create: 2024/9/15 - 18:38
 * @Description: 商家表
 */
@Component
public interface sellerMapper {
    /**
     * 增
     */
    @Insert("insert into sellers values (null,#{name},#{password},#{description},#{email},#{phone})")
    int addSeller(seller seller);

    /**
     * 删
     */

    @Delete("delete from sellers where SellerID = #{id}")
    int deleteSeller(Integer id);

    /**
     * 改
     */
    @Update("update sellers set SellerName = #{newSeller.name}, Password=#{newSeller.password}, Description=#{newSeller.description}, Email=#{newSeller.email}, PhoneNumber=#{newSeller.phone} where SellerID = #{id};")
    int updateSeller(Integer id, seller newSeller);

    /**
     * 通过 id 查询单个商家信息
     */
    seller selectSellerById(Integer id);

    /**
     * 查询所有商家信息
     */
    List<seller> selectSellerAll();
}
