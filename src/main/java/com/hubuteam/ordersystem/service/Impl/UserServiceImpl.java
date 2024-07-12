package com.hubuteam.ordersystem.service.Impl;

import com.hubuteam.ordersystem.mappers.UserMapper;
import com.hubuteam.ordersystem.pojo.User;
import com.hubuteam.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 返回登录后的用户信息
     */
    @Override
    public User loginUser(String username, String password) {
        User progressUser = userMapper.findByUsername(username);
        if (progressUser != null && progressUser.getPassword().equals(password)) {
            return progressUser;
        }else {
            return null;
        }
    }

    /**
     * 修改用户密码
     *
     * @param userId      用户id
     * @param newPassword 新密码
     * @return 修改用户密码 成功返回1 失败返回0
     */
    @Override
    public int updatePassword(int userId, String newPassword) {
        return userMapper.updateUserPassword(userId, newPassword);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 修改用户信息
     */
    @Override
    public int updateUser(User user) {
        System.out.println("用户信息打印测试2" + user);
        return userMapper.updateUserInfo(user);
    }

    @Override
    public int rigUser(User user) {
        if(userMapper.findByUsername(user.getUsername()) == null){
            return userMapper.insertUser(user);
        }else {
            return -1;
        }

    }
}
