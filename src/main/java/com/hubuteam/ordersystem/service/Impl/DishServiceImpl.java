package com.hubuteam.ordersystem.service.Impl;

import com.hubuteam.ordersystem.mappers.DishMapper;
import com.hubuteam.ordersystem.mappers.MerchantMapper;
import com.hubuteam.ordersystem.pojo.Dish;
import com.hubuteam.ordersystem.pojo.Merchant;
import com.hubuteam.ordersystem.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现:
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    /**
     * 查询所有菜品
     * @return 所有餐品信息
     */
    @Override
    public List<Dish> findAllDishs() {
        return dishMapper.findAll();
    }

    /**
     * 根据id查询菜品
     *
     * @param dishId 菜品id
     * @return 菜品信息
     */
    @Override
    public Dish findDishById(int dishId) {
        Dish newDish = dishMapper.findDishById(dishId);
        newDish.setMerchant(merchantMapper.selectMerchantById(newDish.getMerchantId()));
        return newDish;
    }

    /**
     * 插入菜品
     *
     * @param merchantID   商家id
     * @param dishName     菜品名
     * @param description  菜品描述
     * @param price        菜品价格
     * @param imageUrlPath 菜品图片地址
     * @return 插入菜品是否成功
     */
    @Override
    public int insertDish(int merchantID, String dishName, String description, double price, String imageUrlPath) {
        Dish dish = new Dish();
        dish.setMerchantId(merchantID);
        dish.setDishName(dishName);
        dish.setDescription(description);
        dish.setPrice(price);
        dish.setImageUrl(imageUrlPath);
        return dishMapper.insertDish(dish);
    }

    /**
     * 更新菜品
     *
     * @param dish 新菜品信息
     * @return 更新菜品是否成功
     */
    @Override
    public int updateDish(Dish dish) {
        return dishMapper.updateDish(dish);
    }

    /**
     * 删除菜品
     *
     * @param dishId 菜品id
     * @return 删除菜品是否成功
     */
    @Override
    public int deleteDish(int dishId) {
        return dishMapper.deleteDish(dishId);
    }
}
