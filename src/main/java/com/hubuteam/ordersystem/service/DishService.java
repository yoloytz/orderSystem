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
     * @return 所有菜品信息
     */
    List<Dish> findAllDishs();

    /**
     * 根据id查询菜品
     * @param dishId 菜品id
     * @return 菜品信息
     */
    Dish findDishById(int dishId);

    /**
     * 插入菜品
     * @param merchantID 商家id
     * @param dishName 菜品名
     * @param description 菜品描述
     * @param price 菜品价格
     * @param imageUrlPath 菜品图片地址
     * @return 插入菜品是否成功
     */
    int insertDish(int merchantID,String dishName, String description,double price,String imageUrlPath);

    /**
     * 更新菜品
     * @param dish 新菜品信息
     * @return 更新菜品是否成功
     */
    int updateDish(Dish dish);


    /**
     * 删除菜品
     * @param dishId 菜品id
     * @return 删除菜品是否成功
     */
    int deleteDish(int dishId);
}
