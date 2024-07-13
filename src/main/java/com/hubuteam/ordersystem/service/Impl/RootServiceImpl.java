package com.hubuteam.ordersystem.service.Impl;

import com.hubuteam.ordersystem.mappers.RootMapper;
import com.hubuteam.ordersystem.mappers.UserMapper;
import com.hubuteam.ordersystem.pojo.Admin;
import com.hubuteam.ordersystem.pojo.User;
import com.hubuteam.ordersystem.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-13
 * QQ：2311170320
 * 功能实现:
 */
@Service
public class RootServiceImpl implements RootService {
    @Autowired
    private RootMapper rootMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 管理员登录
     *
     * @param username 管理员账号
     * @param password 管理员密码
     * @return 管理员信息
     */
    @Override
    public Admin adminLogin(String username, String password) {
        Admin nowAdmin = rootMapper.findRootByRootName(username);
        if (nowAdmin != null && nowAdmin.getPassword().equals(password)) {
            return nowAdmin;
        }else {
            return null;
        }
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 获取所有用户信息
     *
     * @return 获取用户信息
     */
    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }
}
