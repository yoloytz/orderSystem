package com.hubuteam.ordersystem.controller;

import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.pojo.Review;
import com.hubuteam.ordersystem.pojo.User;
import com.hubuteam.ordersystem.service.DishService;
import com.hubuteam.ordersystem.service.Impl.UserServiceImpl;
import com.hubuteam.ordersystem.service.OrderService;
import com.hubuteam.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            return refreshView(model, nowUser,"登陆成功");
        }else{
            model.addAttribute("msg","用户登录账号或密码错误");
            return "error";
        }
    }


    @PostMapping("/userPostReview")
    public String userPostReview(HttpSession session, @RequestParam("orderId") int orderId,
                                 @RequestParam("rating") int rating, @RequestParam("comment") String comment, Model model) {
        User user = (User) session.getAttribute("user");
        System.out.println("oderId" + orderId);
        System.out.println("rating" + rating);
        System.out.println("comment" + comment);
        System.out.println("成功提交评论测试01");
        if (user == null) {
            return "redirect:/login";
        }else {
            System.out.println("user" + user.toString());
        }
        Review review = new Review();
        review.setOrder(orderService.findOrderById(orderId));
        review.setUser(user);
        review.setRating(rating);
        review.setComment(comment);
        orderService.saveReview(review);
        return refreshView(model, user,"评论提交成功！");
    }

    @PostMapping("/deleteOrder")
    public String deleteOrder(HttpSession session, @RequestParam("orderId") int orderId, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        // 调用服务层方法删除订单
        orderService.deleteOrderById(orderId);
        // 刷新视图
        return refreshView(model, user,"删除订单成功！");
    }

    @PostMapping("/updateUser")
    public String updateUser(HttpSession session, Model model,
                             @RequestParam("username") String username,
                             @RequestParam("phone") String phone,
                             @RequestParam("address") String address) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        user.setUsername(username);
        user.setPhone(phone);
        user.setAddress(address);
        System.out.println("用户信息打印测试1" + user);
        userService.updateUser(user);

        return refreshView(model, user,"修改信息成功");
    }

    @PostMapping("/updatePassword")
    public String updatePassword(HttpSession session, Model model,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        if (!newPassword.equals(confirmPassword)) {
            return refreshView(model, user,"新密码和确认密码不匹配");
        }
        if (user.getPassword().equals(oldPassword)) {
            userService.updatePassword(user.getUserID(), newPassword);
            return refreshView(model, user,"密码更新成功！");
        }else {
            return refreshView(model, user,"旧密码错误");
        }

    }


    private String refreshView(Model model, User user,String msg) {
        model.addAttribute("dishes", dishService.findAllDishs());
        List<Order> orders = orderService.selectOrdersByUserIdService(user.getUserID());
        orders.forEach(order -> {
            order.setOrderDetails(orderService.orderDetailsByOrderIdService(order.getOrderId()));
            order.setReview(orderService.reviewByOrderIdService(order.getOrderId()));
        });
        model.addAttribute("orders", orders);
        model.addAttribute("msg",msg);
        return "userView";
    }


}
