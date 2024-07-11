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
 * ReviewID：评价唯一标识符，主键，自动递增。
 * OrderID：订单唯一标识符，外键，关联到订单表。
 * UserID：用户唯一标识符，外键，关联到用户表。
 * Rating：评价评分（1到5星），不为空。
 * Comment：评价内容。
 * CreatedAt：评价时间，默认为当前时间戳。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long reviewID;
    private Order order;
    private User user;
    private Integer rating;
    private String comment;
    private java.util.Date createdAt = new java.util.Date();
}