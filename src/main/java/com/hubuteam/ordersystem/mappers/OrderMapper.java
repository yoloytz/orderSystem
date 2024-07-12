package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.pojo.OrderDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现: 订单持久层
 */
@Mapper
public interface OrderMapper {
    /**
     * 根据用户id查询订单
     * @param userId 用户id
     * @return 用户订单
     */
    @Select("SELECT * FROM ordersys.orders WHERE UserID = #{userId}")
    List<Order> selectOrdersByUserId(int userId);

    /**
     * 根据订单id返回订单信息
     * @param orderId 订单编号
     * @return 订单信息
     */
    @Select("SELECT * FROM ordersys.orders WHERE OrderID = #{orderId}")
    Order selectOrderById(int orderId);

    /**
     * 根据订单id返回订单详情
     * @param orderId 订单id
     * @return 订单详情
     */
    @Select("SELECT * FROM ordersys.orderdetails where OrderID = #{orderId}")
    List<OrderDetail> selectOrderDetailsByOrderId(int orderId);

    /**
     * 订单完成
     * @param orderId 订单id
     * @return 返回修改的行数 1 为成功 0 为失败
     */
    @Update("UPDATE ordersys.orders SET Status = 'rated' WHERE OrderID = #{orderId}")
    int updateStatusCompleted(int orderId);

    /**
     * 删除订单
     * @param orderId 订单编号
     * @return 删除订单成功返回1 删除订单失败返回0
     */
    @Delete("DELETE FROM ordersys.orders WHERE OrderID = #{orderId}")
    int deleteOrder(int orderId);


    /**
     * 保存订单
     * @param order 订单信息
     * @return 成功返回1 失败返回0
     */
    int saveOrder(@Param("m_order") Order order);

    /**
     * 保存订单详情
     * @param orderDetail 订单详情
     * @return 成功返回1 失败返回0
     */
    int saveOrderDetail(@Param("m_orderDetail") OrderDetail orderDetail);
}
