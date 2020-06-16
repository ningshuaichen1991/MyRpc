package com.rpc.v5;

import com.rpc.common.IOrderService;
import com.rpc.common.Order;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Stub stub = new Stub();
        IOrderService service =  stub.getStup(IOrderService.class);
        long s = System.currentTimeMillis();
        for(int i = 0;i<10000;i++){
            Order order = service.findOrderById(789);
        }
        System.out.println(System.currentTimeMillis() - s);
    }
}
