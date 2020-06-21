package com.rpc.v3;
import	java.math.BigDecimal;

import com.rpc.common.IOrderService;
import com.rpc.common.Order;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Stub stub = new Stub();
        IOrderService service = stub.getStup();

        Order order = service.findOrderById(789);
        System.out.println("查询订单返回结果："+order);

        String result = service.createOrder(new Order(BigDecimal.valueOf(3000)));
        System.out.println("创建订单返回结果："+result);
    }
}
