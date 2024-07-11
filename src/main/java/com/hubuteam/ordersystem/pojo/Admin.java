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
 * * 管理员实体类
 * AdminID：管理员唯一标识符，主键，自动递增。
 * Username：管理员用户名，唯一，不为空。
 * Password：管理员密码，存储为加密格式，不为空。
 * Email：管理员邮箱，唯一，不为空。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Long adminID;
    private String username;
    private String password;
    private String email;
}