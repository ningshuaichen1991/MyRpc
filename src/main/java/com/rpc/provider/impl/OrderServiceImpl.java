package com.rpc.provider.impl;

import com.rpc.common.IOrderService;
import com.rpc.common.Order;

import java.math.BigDecimal;

/**
 * 订单操作具体实现
 */
public class OrderServiceImpl implements IOrderService {

    @Override
    public Order findOrderById(Integer id) {
        return new Order(id, BigDecimal.valueOf(100));
    }

    @Override
    public String createOrder(Order order) {
        System.out.println("获取order参数对象为:"+order);
        order.setOrderId(999);
        return "创建成功,结果为:"+order;
    }
}