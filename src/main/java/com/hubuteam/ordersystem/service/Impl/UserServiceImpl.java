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
}
