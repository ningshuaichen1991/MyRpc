package com.rpc.v2;

import com.rpc.common.IOrderService;
import com.rpc.common.Order;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Stub {

    public Order findOrderById(int orderId) throws IOException, ClassNotFoundException {

        Socket socket = new Socket("127.0.0.1",9090);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(123);

        socket.getOutputStream().write(bos.toByteArray());
        socket.getOutputStream().flush();

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Order order = (Order)ois.readObject();

        System.out.println("远程调用结果："+order.toString());

        dos.close();
        socket.close();

        return order;
    }
}
