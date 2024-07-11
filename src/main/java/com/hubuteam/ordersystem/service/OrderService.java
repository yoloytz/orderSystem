package com.hubuteam.ordersystem.service;

import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.pojo.OrderDetail;
import com.hubuteam.ordersystem.pojo.Review;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现: 订单接口
 */
public interface OrderService {
    /**
     * 根据用户id查询订单
     * @param userId 用户id
     * @return 用户全部订单
     */
    List<Order> selectOrdersByUserIdService(int userId);

    /**
     * 根据订单id查询订单详情
     * @param orderId 订单id
     * @return 订单详情
     */
    List<OrderDetail> orderDetailsByOrderIdService(int orderId);

    /**
     * 根据订单id查询订单评价
     * @param orderId 订单id
     * @return 订单评价信息
     */
    Review reviewByOrderIdService(int orderId);
}
