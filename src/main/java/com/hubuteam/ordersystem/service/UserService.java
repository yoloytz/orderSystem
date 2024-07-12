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

    /**
     * 修改用户密码
     * @param userId 用户id
     * @param newPassword 新密码
     * @return 修改成功返回1 否则返回0
     */
    int updatePassword(int userId, String newPassword);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 修改用户信息
     */
    int updateUser(User user);

    /**
     * 注册用户
     * @param user 用户信息
     * @return 注册成功返回1 否则返回0
     */
    int rigUser(User user);
}
