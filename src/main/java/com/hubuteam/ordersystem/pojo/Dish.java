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
 * DishID：菜品唯一标识符，主键，自动递增。
 * MerchantID：商家唯一标识符，外键，关联到商家表。
 * DishName：菜品名称，不为空。
 * Description：菜品描述。
 * Price：菜品价格，不为空。
 * ImageURL：菜品图片URL。
 * orderDetails：菜品订单详情列表。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private Long dishId;
    private int merchantId;
    private Merchant merchant;
    private String dishName;
    private String description;
    private Double price;
    private String imageUrl;
    private List<OrderDetail> orderDetails;
}
