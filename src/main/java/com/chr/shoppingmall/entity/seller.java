package com.chr.shoppingmall.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: 程浩然
 * @Create: 2024/9/15 - 18:33
 * @Description: 商家类
 */
@Data
@Component

public class seller {
    private Integer id;
    private String name;
    private String password;
    private String description;
    private String email;
    private String phone;

    public seller() {
    }

    public seller(String name, String password, String description, String email, String phone) {
        this.name = name;
        this.password = password;
        this.description = description;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", Description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
