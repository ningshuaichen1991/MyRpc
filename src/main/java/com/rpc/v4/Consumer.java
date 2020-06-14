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

        IUserService userService = stub.getStup(IUserService.class);
        User user = userService.findById(456);

    }
}
