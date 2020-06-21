package com.rpc.common;

/**
 * 用户接口
 */
public interface IUserService {

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    User findById(Integer id);
}
