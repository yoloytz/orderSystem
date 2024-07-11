package com.hubuteam.ordersystem.service;

import com.hubuteam.ordersystem.pojo.Dish;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现:
 */
public interface DishService {
    /**
     * 查询所有菜品
     * @return List<Dish>
     */
    List<Dish> findAllDishs();

    /**
     * 根据id查询菜品
     * @param dishId 菜品id
     * @return 菜品信息
     */
    Dish findDishById(int dishId);
}
