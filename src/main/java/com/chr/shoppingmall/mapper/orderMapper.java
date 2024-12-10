package com.chr.shoppingmall.mapper;


import com.chr.shoppingmall.entity.order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author: 程浩然
 * @Create: 2024/9/16 - 8:29
 * @Description: 订单表
 */
@Component
public interface orderMapper {
    /**
     * 增
     */
    @Insert("insert into orders values (null, #{userId}, #{productId}, #{sellerId},#{quantity}, #{totalAmount}, #{deliveryAddress},#{recipientName}, #{recipientPhone},#{notes}, #{orderStatus}, #{orderDate}, #{paymentDate},#{shippingDate}, #{deliveryDate})")
    int addOrder(Integer userId, Integer productId, Integer sellerId, Integer quantity, Double totalAmount, String deliveryAddress, String recipientName, String recipientPhone, String orderStatus, String notes, Date orderDate, Date paymentDate, Date shippingDate, Date deliveryDate);

    /**
     * 删
     */
    @Delete("delete from orders where OrderID = #{orderId}")
    int deleteOrder( Integer orderId);

    /**
     * 根据用户id删订单表
     */
    @Delete("delete from orders where UserID = #{userId}")
    int deleteOrderByUserId( Integer userId);

    /**
     * 修改订单状态
     */
    @Update("update Orders set OrderStatus = #{OrderStatus} where OrderID= #{orderId}")
    int updateOrderStatusByOrderId( Integer orderId, String OrderStatus);

    /**
     * 通过userId和 ProductID删除
     */
    @Delete("delete from orders where UserID = #{userId} and ProductID = #{ProductId}")
    int deleteByUserIdProductId( Integer userId,  Integer ProductId);

    /**
     * 根据订单id查询订单
     */
    @Select("select * from Orders where OrderID = #{id}")
    order selectOrderById( Integer id);

    /**
     * 根据用户id查询所有用户订单
     */
    @Select("select * from Orders where UserID = #{userId}")
    List<order> selectOrderByUserId( Integer userId);

    /**
     * 根据商家id查询所有商家订单
     */
    List<order> selectOrderBySellerID( Integer sellerId);

    /**
     * 获取用户订单数量
     */
    @Select("select count(*) from Orders where UserID = #{userId}")
    Integer getNumber( Integer userId);

    /**
     * 获取已签收未评价的商品
     */
    List<order> getNoEvaluationOrder( Integer userId);

    /**
     * 修改订单收货时间
     */
   @Update("update Orders set DeliveryDate = #{deliveryDate} where OrderID= #{orderId}")
    void updateOrderDeliveryDate( Integer orderId, Date deliveryDate);

    /**
     * 修改订单发货时间
     */
    @Update("update Orders set ShippingDate = #{shippingDate} where OrderID= #{orderId}")
    void updateOrderShippingDate(Integer orderId,  Date shippingDate);


}
