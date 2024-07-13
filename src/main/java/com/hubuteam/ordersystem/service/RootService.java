package com.hubuteam.ordersystem.service;

import com.hubuteam.ordersystem.pojo.Admin;
import com.hubuteam.ordersystem.pojo.User;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-13
 * QQ：2311170320
 * 功能实现:
 */
public interface RootService {
    /**
     * 管理员登录
     * @param username 管理员账号
     * @param password 管理员密码
     * @return 管理员信息
     */
    Admin adminLogin(String username, String password);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User findUserByUsername(String username);
    /**
     * 获取所有用户信息
     * @return 获取用户信息
     */
    List<User> getAllUsers();
}
