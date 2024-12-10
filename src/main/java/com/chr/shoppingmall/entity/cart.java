package com.chr.shoppingmall.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: 程浩然
 * @Create: 2024/9/16 - 11:01
 * @Description: 购物车
 */
@Data
@Component
public class cart {
    private Integer cartID;
    private Integer userID;
    private Integer productID;
    private Integer quantity;

    public cart() {
    }

    public cart(Integer userID, Integer productID, Integer quantity) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "cart{" +
                "CartID=" + cartID +
                ", UserID=" + userID +
                ", ProductID=" + productID +
                ", Quantity=" + quantity +
                '}';
    }
}
