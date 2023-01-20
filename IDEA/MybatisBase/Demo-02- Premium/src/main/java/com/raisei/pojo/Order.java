package com.raisei.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer id;
    private Date createTime;
    private Integer price;
    private String remark;
    private Integer userId;

    private User user;

    public Order() {
    }

    public Order(Integer id, Date createTime, Integer price, String remark, Integer userId, User user) {
        this.id = id;
        this.createTime = createTime;
        this.price = price;
        this.remark = remark;
        this.userId = userId;
        this.user = user;
    }
}

