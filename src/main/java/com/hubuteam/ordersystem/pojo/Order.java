package com.hubuteam.ordersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现:
 * OrderID：订单唯一标识符，主键，自动递增。
 * UserID：用户唯一标识符，外键，关联到用户表。
 * MerchantID：商家唯一标识符，外键，关联到商家表。
 * Status：订单状态（已下单、已接单、配送中、已完成、已评价），不为空。
 * TotalPrice：订单总价，不为空。
 * CreatedAt：订单创建时间，默认为当前时间戳。
 * Review：订单评价，外键，关联到评价表。
 * OrderDetails：订单详情，外键，关联到订单详情表。
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int orderId;
    private User user;
    private Merchant merchant;
    private OrderStatus status;
    private Double totalPrice;
    private java.util.Date createdTime = new java.util.Date();
    private List<OrderDetail> orderDetails;
    private Review review;
}
