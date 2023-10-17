package com.raisei.dao;

import com.raisei.pojo.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findAll();
    //根据订单id查询订单，要求把下单用户的信息也查询出来
    Order findById(Integer id);
}
