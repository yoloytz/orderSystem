package com.hubuteam.ordersystem.controller;

import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.pojo.User;
import com.hubuteam.ordersystem.service.DishService;
import com.hubuteam.ordersystem.service.Impl.UserServiceImpl;
import com.hubuteam.ordersystem.service.OrderService;
import com.hubuteam.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现: 用户视图服务器端
 */
@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DishService dishService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/login")
    private String login(HttpSession session, Model model, String username, String password){
        User nowUser = userService.loginUser(username, password);
        if (nowUser != null){
            session.setAttribute("user", nowUser);
            model.addAttribute("dishes", dishService.findAllDishs());
            List<Order> orders = orderService.selectOrdersByUserIdService(nowUser.getUserID());
            orders.forEach(order -> {
                order.setOrderDetails(orderService.orderDetailsByOrderIdService(order.getOrderID()));
                order.setReview(orderService.reviewByOrderIdService(order.getOrderID()));
            });
            model.addAttribute("orders",orders);
            return "userView";
        }else{
            model.addAttribute("msg","用户登录账号或密码错误");
            return "error";
        }
    }


}
