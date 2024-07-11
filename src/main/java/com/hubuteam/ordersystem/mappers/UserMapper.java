package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现: 用户类Mapper
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM ordersys.users WHERE username = #{username}")
    User findByUsername(String username);

}
