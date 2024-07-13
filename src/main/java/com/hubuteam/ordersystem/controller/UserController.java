package com.hubuteam.ordersystem.controller;

import com.hubuteam.ordersystem.pojo.*;
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
import java.util.ArrayList;
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
        int n = orderService.saveReview(review);
        orderService.updateStatusCompleted(orderId);
        if(n == 1){
            return refreshView(model, user,"评论提交成功！");
        }else{
            return refreshView(model, user,"评论提交失败！");
        }

    }

    @PostMapping("/deleteOrder")
    public String deleteOrder(HttpSession session, @RequestParam("orderId") int orderId, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        int n = 0;
        try {
            int j = orderService.deleteOrderDetailsByOrderId(orderId);
            // 调用服务层方法删除订单
            n = orderService.deleteOrderById(orderId);
        } catch (Exception e) {
            return refreshView(model, user,"删除订单失败！不可抗力影响");
        }
        if(n == 1){
            return refreshView(model, user,"删除订单成功！");
        }else{
            return refreshView(model, user,"删除订单失败！不可抗力影响");
        }
        // 刷新视图
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


    @PostMapping("/order")
    public String placeOrder(HttpSession session, Model model, @RequestParam("dishIds") List<Integer> dishIds, @RequestParam("quantities") List<Integer> quantities) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<OrderDetail> orderDetails = new ArrayList<>();
        quantities.removeIf(n -> n == 0);
        for (int i = 0; i < dishIds.size(); i++) {
            int dishId = dishIds.get(i);
            int quantity = quantities.get(i);
            Dish dish = dishService.findDishById(dishId);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDishId(dishId);
            orderDetail.setDish(dish);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(dish.getPrice());
            orderDetails.add(orderDetail);
        }

        Order order = new Order();
        order.setUser(user);
        order.setMerchant(orderDetails.get(0).getDish().getMerchant());
        order.setOrderDetails(orderDetails);
        order.setStatus(OrderStatus.placed);
        order.setTotalPrice(orderDetails.stream().mapToDouble(od -> od.getPrice() * od.getQuantity()).sum());
        int orderId = orderService.saveOrder(order);
        System.out.println("order :" + order);
        for (OrderDetail detail : orderDetails) {
            detail.setOrderId(orderId);
        }
        System.out.println("orderDetails :" + orderDetails);
        orderService.saveOrderDetails(orderDetails);
        return refreshView(model, user,"下单成功！");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpSession session, Model model, String username, String password, String phone, String address) {
        // 处理用户注册逻辑
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        System.out.println(newUser);
        int n = userService.rigUser(newUser);
        if(n == 1){
            model.addAttribute("msg","注册成功！请重新登陆！");
            return "error";
        }else if(n == -1){
            model.addAttribute("msg","用户名已存在！");
            return "error";
        }else {
            model.addAttribute("msg", "注册失败，请重试。");
            return "error";
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
