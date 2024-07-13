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
     * 管理员获取所有订单
     * @return 管理员获取所有订单的服务
     */
    List<Order> getAllOrders();

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

    /**
     * 根据订单id查询订单
     * @param orderId 订单Id
     * @return 订单信息
     */
    Order findOrderById(int orderId);

    /**
     * 保存评论
     * @param review 评论信息
     * @return 评论是否成功
     */
    int saveReview(Review review);

    /**
     * 更新订单状态
     * @param orderId 订单id
     * @return 订单状态是否成功
     */
    int updateStatusCompleted(int orderId);

    /**
     * 更新订单状态
     * @param orderId 订单id
     * @return 订单状态是否成功
     */
    int updateStatusTaken(int orderId);

    /**
     * 更新订单状态
     * @param orderId 订单id
     * @return 订单状态是否成功
     */
    int updateStatusDelivery(int orderId);

    /**
     * 根据订单id删除订单
     * @param orderId 订单id
     * @return 订单是否删除成功
     */
    int deleteOrderById(int orderId);


    /**
     * 保存订单
     * @param order 保存订单
     * @return 成功返回订单id 失败返回-1
     */
    int saveOrder(Order order);


    /**
     * 保存订单详情
     * @param orderDetails 保存订单详情
     */
    void saveOrderDetails(List<OrderDetail> orderDetails);


    /**
     * 根据订单id删除订单详情
     * @param orderId 订单id
     * @return 订单内菜品数量
     */
    int deleteOrderDetailsByOrderId(int orderId);
}
