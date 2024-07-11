package com.hubuteam.ordersystem.service.Impl;

import com.hubuteam.ordersystem.mappers.OrderMapper;
import com.hubuteam.ordersystem.mappers.ReviewMapper;
import com.hubuteam.ordersystem.pojo.Dish;
import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.pojo.OrderDetail;
import com.hubuteam.ordersystem.pojo.Review;
import com.hubuteam.ordersystem.service.DishService;
import com.hubuteam.ordersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现:
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DishService dishService;
    @Autowired
    private ReviewMapper reviewMapper;
    /**
     * 根据用户id查询订单
     * @param userId 用户id
     * @return 用户全部订单
     */
    @Override
    public List<Order> selectOrdersByUserIdService(int userId) {
        return orderMapper.selectOrdersByUserId(userId);
    }

    /**
     * 根据订单id查询订单详情
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    @Override
    public List<OrderDetail> orderDetailsByOrderIdService(int orderId) {
        List<OrderDetail> orderDetails = orderMapper.selectOrderDetailsByOrderId(orderId);
        for(OrderDetail detail : orderDetails){
            detail.setDish(dishService.findDishById(detail.getDishId()));
        }
        return orderDetails;
    }

    /**
     * 根据订单id查询订单评价
     *
     * @param orderId 订单id
     * @return 订单评价信息
     */
    @Override
    public Review reviewByOrderIdService(int orderId) {
        return reviewMapper.selectReviewByOrderId(orderId);
    }
}
