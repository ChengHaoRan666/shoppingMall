package com.chr.shoppingmall.entity;

import lombok.Data;

/**
 * @Author: 程浩然
 * @Create: 2024/12/12 - 21:30
 * @Description: 商品和评分
 */
@Data
public class productAndRanting {
    product product;
    Integer ranting;

    public productAndRanting(product product, Integer ranting) {
        this.product = product;
        this.ranting = ranting;
    }
}