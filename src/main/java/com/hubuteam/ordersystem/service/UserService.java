package com.hubuteam.ordersystem.service;

import com.hubuteam.ordersystem.pojo.User;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现:
 */
public interface UserService {
    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 返回登录后的用户信息
     */
    User loginUser(String username, String password);
}
