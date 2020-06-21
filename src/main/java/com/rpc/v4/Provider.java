package com.rpc.v4;
import	java.util.ArrayList;

import com.rpc.provider.impl.OrderServiceImpl;
import com.rpc.provider.impl.UserServiceImpl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Provider {

    public static boolean isRunning = true;

    //服务接口集合
    private static List<Object> serviceList = new ArrayList<Object> ();

    static {
        serviceList.add(new OrderServiceImpl());
        serviceList.add(new UserServiceImpl());
    }

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

        Class interfaze = (Class)ois.readObject();//接口
        String methodName = ois.readUTF();//方法名称
        Class [] parameterTypes = (Class []) ois.readObject();//参数类型
        Object [] args = (Object []) ois.readObject();//参数


        ObjectOutputStream oos = new ObjectOutputStream(os);

        Object service = getServiceByInterfaze(interfaze);

        Class serviceClass=service.getClass();
        Method  method = serviceClass.getMethod(methodName,parameterTypes);

        Object o =  method.invoke(service,args);

        oos.writeObject(o);
        oos.flush();
    }

    /**
     * 通过interface获取clazz的子类/实现类
     * @param clazz
     * @return
     */
    public static Object getServiceByInterfaze(Class clazz){
        for (Object obj : serviceList) {
            boolean isFather = clazz.isAssignableFrom(obj.getClass());
            if (isFather) {
                return obj;
            }
        }
        return null;
    }
}