package com.rpc.common;
import	java.io.Serializable;

import lombok.Data;

/**
 * 用户
 */
@Data
public class User implements Serializable{
    /**
     * id
     */
    private Integer id;
    /**
     * 用户名称
     */
    private String userName;

    public User(Integer id,String userName){
        this.id = id;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}