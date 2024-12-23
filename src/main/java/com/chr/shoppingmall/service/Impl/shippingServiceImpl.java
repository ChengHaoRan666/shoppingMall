package com.chr.shoppingmall.service.Impl;


import com.chr.shoppingmall.dao.*;
import com.chr.shoppingmall.entity.*;
import com.chr.shoppingmall.service.shippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: 程浩然
 * @Create: 2024/9/18 - 16:00
 */
@Service
public class shippingServiceImpl implements shippingService {
    @Autowired
    private orderDao orderDao;
    @Autowired
    private productDao productDao;
    @Autowired
    private cartDao cartDao;
    @Autowired
    private starDao starDao;
    @Autowired
    private reviewDao reviewDao;
    @Autowired
    private userDao userDao;


    /**
     * 创建订单
     */
    @Override
    public void orderCreate(String name, String phone, String address, String notes, Integer Userid, Map<Integer, Integer> productIdsAndQuantity) {
        for (Map.Entry<Integer, Integer> entry : productIdsAndQuantity.entrySet()) {
            Integer productId = Integer.parseInt(String.valueOf(entry.getKey()));
            Integer count = Integer.parseInt(String.valueOf(entry.getValue()));
            product product = productDao.selectProductById(productId);
            orderDao.addOrder(Userid, productId, product.getProductStoreID(), count, count * product.getPrice(), address, name, phone, "1", notes, new Date(), new Date(), null, null);
        }
    }

    /**
     * 添加到购物车
     */
    @Override
    public void addCart(Integer userId, Integer productId, Integer quantity) {
        cartDao.addCart(new cart(userId, productId, quantity));
    }

    /**
     * 加入收藏
     */
    @Override
    public void addStar(Integer userId, Integer productId, Integer quantity) {
        starDao.addStar(new star(userId, productId, quantity));
    }


    /**
     * 评价商品
     */
    @Override
    public void evaluate(Integer userId, Integer productId, Integer rating, String comment) {
        reviewDao.addReview(new review(productId, userId, rating, comment, new Date()));
    }



    /**
     * 修改订单状态
     */
    @Override
    public void delivery(Integer orderId, String OrderStatus) {
        orderDao.updateOrderStatusByOrderId(orderId, OrderStatus);
    }

    /**
     * 根据用户id和商品id删除订单
     */
    @Override
    public void deleteOrderByUserIdProductId(Integer userId, Integer productId) {
        orderDao.deleteByUserIdProductId(userId, productId);
    }

    /**
     * 通过orderId删除
     */
    @Override
    public void deleteOrderByOrderId(Integer orderId) {
        orderDao.deleteOrder(orderId);
    }

    /**
     * 根据用户id和商品id删除收藏
     */
    @Override
    public void deleteStarByUserIdProductId(Integer userId, Integer productId) {
        starDao.deleteStarByUserIdProductId(userId, productId);
    }

    /**
     * 根据用户id和商品id删除购物车
     */
    @Override
    public void deleteCarByUserIdProductId(Integer userId, Integer productId) {
        cartDao.deleteCartByUserIdProductId(userId, productId);
    }

    /**
     * 通过orderId获取productId
     */
    @Override
    public List<Integer> getProductIdByOrderId(List<Integer> orderIdList) {
        List<Integer> productIdList = new ArrayList<>();
        for (Integer orderId : orderIdList) {
            productIdList.add(orderDao.selectOrderById(orderId).getProductId());
        }
        return productIdList;
    }

    /**
     * 通过orderId获取productId
     */
    @Override
    public Integer getProductIdByOrderId(Integer orderId) {
        return orderDao.selectOrderById(orderId).getProductId();
    }

    /**
     * 获取已收货未评价订单
     */
    public List<order> getNoEvaluationOrder(Integer userId) {
        return orderDao.getNoEvaluationOrder(userId);
    }

    /**
     * 设置订单收货时间
     */
    @Override
    public void setOrderDeliveryDate(Integer orderId, Date deliveryDate) {
        orderDao.updateOrderDeliveryDate(orderId, deliveryDate);
    }

    /**
     * 设置订单发货时间
     */
    @Override
    public void setOrderShippingDate(Integer orderId, Date shippingDate) {
        orderDao.updateOrderShippingDate(orderId, shippingDate);
    }

    /**
     * 通过用户id获取用户名
     */
    @Override
    public String getUserNameByUserId(Integer userId) {
        user user = userDao.selectUserById(userId);
        return user.getUsername();
    }
}
