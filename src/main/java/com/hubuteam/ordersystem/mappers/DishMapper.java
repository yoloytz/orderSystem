package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现: 实现菜单数据库Mapper映射
 */
@Mapper
public interface DishMapper {
    /**
     * 查询所有菜品
     * @return 菜单表
     */
    @Select("SELECT * FROM ordersys.dishes")
    List<Dish> findAll();

    /**
     * 根据ID查询菜品
     * @param dishId 菜品ID
     * @return 菜品
     */
    @Select("SELECT * FROM ordersys.dishes WHERE dishID = #{dishId}")
    Dish findDishById(int dishId);
}
