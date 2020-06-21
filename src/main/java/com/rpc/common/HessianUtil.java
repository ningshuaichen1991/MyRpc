package com.rpc.common;

import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class HessianUtil {


    public static void main(String[] args) throws IOException {

        User u = new User(1,"222");
        byte b [] = jdkSerialize(u);
        System.out.println("jdk："+b.length);

        byte c [] = hessianSerialize(u);
        System.out.println("hessian："+c.length);
    }

    /**
     * 序列化
     * @param o
     * @return
     * @throws IOException
     */
    public static byte[] hessianSerialize(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(baos);
        out.writeObject(o);
        out.flush();
        byte b[] = baos.toByteArray();
        baos.close();
        out.close();
        return b;
    }

    public static byte[] jdkSerialize(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        byte b[] = baos.toByteArray();
        baos.close();
        oos.close();
        return b;
    }

}