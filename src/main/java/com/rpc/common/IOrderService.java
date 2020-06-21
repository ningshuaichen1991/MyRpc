package com.rpc.common;

/**
 * 订单
 */
public interface IOrderService {

    /**
     * 根据订单号查询订单
     * @param id
     * @return
     */
    Order findOrderById(Integer id);

    /**
     * 创建订单
     * @param order
     * @return
     */
    String createOrder(Order order);
}