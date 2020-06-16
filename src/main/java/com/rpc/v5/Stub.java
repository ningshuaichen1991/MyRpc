package com.rpc.v5;

import com.caucho.hessian.io.Hessian2Input;

import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {

    public <T> T getStup(Class<T> cl){
        InvocationHandler handler = new InvocationHandler() {
            //被代理对象方法调用的时候，该怎么处理就在此方法中执行
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1",9090);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                //把参数类型、方法名称、参数传输到服务提供方
                String methodName = method.getName();
                Class[] parametersTypes = method.getParameterTypes();
                oos.writeObject(cl);
                oos.writeUTF(methodName);
                oos.writeObject(parametersTypes);
                oos.writeObject(args);
                oos.flush();

                Hessian2Input input = new Hessian2Input(socket.getInputStream());
                Object o = input.readObject();

                //System.out.println("远程调用结果："+o.toString());

                oos.close();
                socket.close();
                return o;
            }
        };
        //一旦被调用成功，就会返回一个代理对象，并且是动态生成一个$IOrderServiceProxy.class
        Object o = Proxy.newProxyInstance(cl.getClassLoader(), new Class[] {cl}, handler);
        return (T)o;

    }
}