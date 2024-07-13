package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.Dish;
import org.apache.ibatis.annotations.*;

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


    /**
     * 插入菜品
     * @param dish 菜品信息
     * @return 插入菜品成功返回1，否则返回0
     */
    int insertDish(@Param("dish") Dish dish);

    /**
     * 更新菜品
     * @param dish 新菜品信息
     * @return 更新菜品成功返回1，否则返回0
     */
    int updateDish(@Param("dish") Dish dish);

    /**
     * 删除菜品
     * @param dishId 菜品信息
     * @return 删除菜品成功返回1，否则返回0
     */
    @Delete("DELETE FROM ordersys.dishes WHERE DishID = #{dishId}")
    int deleteDish(@Param("dishId") int dishId);
}
