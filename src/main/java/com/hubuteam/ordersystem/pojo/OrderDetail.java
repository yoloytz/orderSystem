package com.hubuteam.ordersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现:
 * OrderDetailID：订单详情唯一标识符，主键，自动递增。
 * OrderID：订单唯一标识符，外键，关联到订单表。
 * DishID：菜品唯一标识符，外键，关联到菜品表。
 * Quantity：菜品数量，不为空。
 * Price：菜品价格，不为空。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int dishId;
    private Dish dish;
    private Integer quantity;
    private Double price;
}