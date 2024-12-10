package com.chr.shoppingmall.mapper;


import com.chr.shoppingmall.entity.review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 程浩然
 * @Create: 2024/9/16 - 10:07
 * @Description: 评价表
 */
@Component
public interface reviewMapper {
    /**
     * 增
     */
    @Insert("insert into reviews values (null, #{review.productId}, #{review.userId}, #{review.rating}, #{review.comment}, #{review.reviewTime});")
    int addReview(review review);

    /**
     * 通过商品id查找
     */
    @Select("select * from Reviews where ProductID = #{productId}")
    List<review> selectReviewByproductId(Integer productId);

    /**
     * 通过商品id删除
     */
    @Delete("delete from reviews where ProductID=#{productId}")
    int deleteByproductId(Integer productId);
}
