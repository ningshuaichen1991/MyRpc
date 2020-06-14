package com.rpc.v3;

import com.rpc.common.IOrderService;
import com.rpc.common.Order;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Stub stub = new Stub();
        IOrderService service = stub.getStup();
        Order order = service.findOrderById(789);

        System.out.println(order);
    }
}
