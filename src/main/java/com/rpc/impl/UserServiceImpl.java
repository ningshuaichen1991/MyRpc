package com.rpc.impl;

import com.rpc.common.IUserService;
import com.rpc.common.User;

public class UserServiceImpl implements IUserService {
    @Override
    public User findById(Integer id) {
        return new User(id,"张三");
    }
}