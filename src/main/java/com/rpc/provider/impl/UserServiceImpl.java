package com.rpc.provider.impl;

import com.rpc.common.IUserService;
import com.rpc.common.User;

public class UserServiceImpl implements IUserService {
    @Override
    public User findById(Integer id) {
        return new User(id,"张三");
    }
}