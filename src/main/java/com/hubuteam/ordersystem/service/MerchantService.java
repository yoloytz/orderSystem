package com.hubuteam.ordersystem.service;

import com.hubuteam.ordersystem.pojo.Merchant;
import com.hubuteam.ordersystem.pojo.Order;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-12
 * QQ：2311170320
 * 功能实现:
 */
public interface MerchantService {
    /**
     * 商家登录
     * @param merchantName 商家名
     * @param password 密码
     * @return 登录成功返回1，失败返回0
     */
    Merchant merchantLogin(String merchantName, String password);

    /**
     * 获取所有订单
     * @param merchantId 商户id
     * @return 商家订单
     */
    List<Order> getAllOrders(int merchantId);
}
