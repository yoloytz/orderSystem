package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-12
 * QQ：2311170320
 * 功能实现: 餐厅持久层接口
 */
@Mapper
public interface MerchantMapper {

    /**
     * 根据id查询餐厅信息
     * @param merchantId 餐厅id
     * @return 餐厅信息
     */
    @Select("SELECT * FROM ordersys.merchants WHERE MerchantID = #{merchantId}")
    Merchant selectMerchantById(@Param("merchantId") int merchantId);

    /**
     * 根据商家名查询商家信息
     * @param merchantName 商家名
     * @return 商家信息
     */
    @Select("SELECT * FROM ordersys.merchants WHERE MerchantName = #{m_merchantName}")
    Merchant selectMerchantByUsername(@Param("m_merchantName") String merchantName);

}
