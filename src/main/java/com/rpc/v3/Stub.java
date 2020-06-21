package com.rpc.v3;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import	java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import com.rpc.common.IOrderService;

public class Stub {

    public IOrderService getStup(){
        InvocationHandler handler = new InvocationHandler() {
            //被代理对象方法调用的时候，该怎么处理就在此方法中执行
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1",9090);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                //把参数类型、方法名称、参数传输到服务提供方
                String methodName = method.getName();
                Class[] parametersTypes = method.getParameterTypes();
                oos.writeUTF(methodName);
                oos.writeObject(parametersTypes);
                oos.writeObject(args);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object o = ois.readObject();

                oos.close();
                socket.close();
                return o;
            }
        };
        //一旦被调用成功，就会返回一个代理对象，并且是动态生成一个$IOrderServiceProxy.class
        Object o = Proxy.newProxyInstance(IOrderService.class.getClassLoader(), new Class[] {IOrderService.class}, handler);
        return (IOrderService)o;

    }
}