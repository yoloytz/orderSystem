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
 * 功能实现: 用户类
 * UserID：用户唯一标识符，主键，自动递增。
 * Username：用户名，唯一，不为空。
 * Password：用户密码，存储为加密格式，不为空。
 * Phone：用户电话号码。
 * Address：用户地址。
 * Orders：用户订单列表。
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userID;
    private String username;
    private String password;
    private String phone;
    private String address;
    private List<Order> orders;
}