package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
     * 根据订单id返回订单详情
     * @param orderId 订单id
     * @return 订单详情
     */
    @Select("SELECT * FROM ordersys.orderdetails where OrderID = #{orderId}")
    List<OrderDetail> selectOrderDetailsByOrderId(int orderId);

}
