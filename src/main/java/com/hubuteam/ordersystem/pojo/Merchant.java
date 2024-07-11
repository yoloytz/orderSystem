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
 * MerchantID：商家唯一标识符，主键，自动递增。
 * MerchantName：商家名称，唯一，不为空。
 * Password：商家密码，存储为加密格式，不为空。
 * Email：商家邮箱，唯一，不为空。
 * Phone：商家电话号码。
 * Address：商家地址。
 * Dishes：商家提供的菜品列表，与Dish表关联。
 * Orders：商家提供的订单列表，与Order表关联。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    private Long merchantID;
    private String merchantName;
    private String password;
    private String email;
    private String phone;
    private String address;
    private List<Dish> dishes;
    private List<Order> orders;
}