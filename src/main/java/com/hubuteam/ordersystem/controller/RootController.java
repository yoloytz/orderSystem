package com.hubuteam.ordersystem.controller;

import com.hubuteam.ordersystem.pojo.Admin;
import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.pojo.User;
import com.hubuteam.ordersystem.service.DishService;
import com.hubuteam.ordersystem.service.OrderService;
import com.hubuteam.ordersystem.service.RootService;
import com.hubuteam.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-13
 * QQ：2311170320
 * 功能实现:
 */
@Controller
@RequestMapping("/Root")
public class RootController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RootService rootService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    private String login(HttpSession session, Model model, String username, String password){
        Admin nowAdmin = rootService.adminLogin(username, password);
        if (nowAdmin != null){
            session.setAttribute("root", nowAdmin);
            return refreshView(model, nowAdmin,"登陆成功");
        }else{
            model.addAttribute("msg","用户登录账号或密码错误");
            return "error";
        }
    }

    @PostMapping("/deleteOrder")
    public String deleteOrder(HttpSession session, @RequestParam("orderId") int orderId, Model model) {
        Admin admin = (Admin) session.getAttribute("root");
        if (admin == null) {
            return "redirect:/login";
        }
        int n = 0;
        try {
            int j = orderService.deleteOrderDetailsByOrderId(orderId);
            // 调用服务层方法删除订单
            n = orderService.deleteOrderById(orderId);
        } catch (Exception e) {
            return refreshView(model, admin,"检测外键依赖");
        }
        if(n == 1){
            return refreshView(model, admin,"删除订单成功！");
        }else{
            return refreshView(model, admin,"检测外键依赖");
        }
        // 刷新视图
    }

    @PostMapping("updateUserInfo")
    public String updateUserInfo(HttpSession session, Model model,
                                 @RequestParam("username") String username,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("address") String address,
                                 @RequestParam("oldUserName") String oldUserName) {
        Admin admin = (Admin) session.getAttribute("root");
        if (admin == null) {
            return "redirect:/login";
        }
        System.out.println("username" + oldUserName);
        User user = rootService.findUserByUsername(oldUserName);

        if (user == null) {
            return refreshView(model, admin, "用户未找到");
        }
        user.setUsername(username);
        user.setPhone(phone);
        user.setAddress(address);
        userService.updateUser(user);
        return refreshView(model, admin, "修改信息成功");
    }

    private String refreshView(Model model,Admin nowAdmin,String msg) {
        List<Order> orders = orderService.getAllOrders();
        orders.forEach(order -> {
            order.setOrderDetails(orderService.orderDetailsByOrderIdService(order.getOrderId()));
            order.setReview(orderService.reviewByOrderIdService(order.getOrderId()));
        });
        List<User> userList = rootService.getAllUsers();
        model.addAttribute("userList", userList);
        model.addAttribute("orders", orders);
        model.addAttribute("msg",msg);
        return "rootView";
    }
}
