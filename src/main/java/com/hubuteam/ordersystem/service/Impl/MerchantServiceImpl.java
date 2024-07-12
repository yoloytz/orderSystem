package com.hubuteam.ordersystem.service.Impl;

import com.hubuteam.ordersystem.mappers.MerchantMapper;
import com.hubuteam.ordersystem.mappers.OrderMapper;
import com.hubuteam.ordersystem.pojo.Merchant;
import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-12
 * QQ：2311170320
 * 功能实现:
 */
@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 商家登录
     *
     * @param merchantName 商家名
     * @param password     密码
     * @return 登录成功返回1，失败返回0
     */
    @Override
    public Merchant merchantLogin(String merchantName, String password) {
        Merchant merchant =  merchantMapper.selectMerchantByUsername(merchantName);
        System.out.println("merchant测试01 "  + merchant);
        if(merchant != null && merchant.getPassword().equals(password)){
            return merchant;
        }else {
            return null;
        }
    }

    /**
     * 获取所有订单
     *
     * @param merchantId 商户id
     * @return 商家订单
     */
    @Override
    public List<Order> getAllOrders(int merchantId) {
        return orderMapper.selectAllOrdersByMerchantId(merchantId);
    }
}
