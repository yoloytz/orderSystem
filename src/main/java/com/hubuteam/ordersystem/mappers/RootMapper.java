package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-13
 * QQ：2311170320
 * 功能实现:
 */
@Mapper
public interface RootMapper {

    /**
     * 根据管理员名称查询管理员信息
     * @param username 管理员名称
     * @return 管理员信息
     */
    @Select("SELECT * FROM ordersys.admins WHERE Username = #{username}")
    Admin findRootByRootName(@Param("username") String username);
}
