package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.Review;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现: 对评论信息的增删改查
 */
@Mapper
public interface ReviewMapper {
    /**
     * 根据订单id查询评论信息
     * @param orderId 订单id
     * @return 评论信息
     */
    @Select("SELECT * FROM ordersys.reviews WHERE OrderID = #{orderId}")
    Review selectReviewByOrderId(int orderId);



    int insertReview(@Param("review") Review review);
}
