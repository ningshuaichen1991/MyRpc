package com.rpc.v4;

import com.rpc.common.IOrderService;
import com.rpc.common.IUserService;
import com.rpc.common.Order;
import com.rpc.common.User;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Stub stub = new Stub();
        IOrderService service =  stub.getStup(IOrderService.class);
        Order order = service.findOrderById(789);

        System.out.println("订单服务调用结果："+order);

        IUserService userService = stub.getStup(IUserService.class);
        User user = userService.findById(456);

        System.out.println("用户服务调用结果："+order);

    }
}
