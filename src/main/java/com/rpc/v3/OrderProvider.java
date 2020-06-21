package com.rpc.v3;
import java.lang.reflect.InvocationTargetException;
import	java.lang.reflect.Method;

import com.rpc.common.IOrderService;
import com.rpc.provider.impl.OrderServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class OrderProvider {

    public static boolean isRunning = true;

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        ServerSocket serverSocket = new ServerSocket(9090);
        while(isRunning){
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }
        serverSocket.close();
    }

    public static void process(Socket s) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        ObjectInputStream ois = new ObjectInputStream(is);

        String methodName = ois.readUTF();
        Class [] parameterTypes = (Class []) ois.readObject();
        Object [] args = (Object []) ois.readObject();



        ObjectOutputStream oos = new ObjectOutputStream(os);

        IOrderService service = new OrderServiceImpl();

        Class serviceClass=service.getClass();
        Method  method = serviceClass.getMethod(methodName,parameterTypes);

        Object o =  method.invoke(service,args);

        oos.writeObject(o);
        oos.flush();
    }
}