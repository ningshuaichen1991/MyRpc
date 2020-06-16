package com.rpc.v5;

import com.caucho.hessian.io.Hessian2Output;
import com.rpc.impl.OrderServiceImpl;
import com.rpc.impl.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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



        Hessian2Output output = new Hessian2Output(os);

        Object service = getServiceByInterfaze(interfaze);

        Class serviceClass = service.getClass();
        Method  method = serviceClass.getMethod(methodName,parameterTypes);

        Object o =  method.invoke(service,args);

        output.writeObject(o);
        output.flush();
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