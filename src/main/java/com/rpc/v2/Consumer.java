package com.rpc.v2;

import com.rpc.common.Order;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Stub stub = new Stub();
        Order order = stub.findOrderById(456);

        System.out.println(order);
    }
}
