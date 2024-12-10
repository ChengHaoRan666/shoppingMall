package com.chr.shoppingmall.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: 程浩然
 * @Create: 2024/9/16 - 11:01
 * @Description: 收藏表
 */
@Data
@Component

public class star {
    private Integer starID;
    private Integer userID;
    private Integer productID;
    private Integer quantity;

    public star() {
    }

    public star(Integer userID, Integer productID, Integer quantity) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "star{" +
                "StarID=" + starID +
                ", UserID=" + userID +
                ", ProductID=" + productID +
                ", Quantity=" + quantity +
                '}';
    }
}
