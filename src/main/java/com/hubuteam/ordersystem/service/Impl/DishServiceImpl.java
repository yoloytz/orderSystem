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
     * @return List<Dish>
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
}
