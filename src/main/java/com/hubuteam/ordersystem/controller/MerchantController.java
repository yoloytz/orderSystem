package com.hubuteam.ordersystem.controller;

import com.hubuteam.ordersystem.pojo.Dish;
import com.hubuteam.ordersystem.pojo.Merchant;
import com.hubuteam.ordersystem.pojo.Order;
import com.hubuteam.ordersystem.pojo.User;
import com.hubuteam.ordersystem.service.DishService;
import com.hubuteam.ordersystem.service.MerchantService;
import com.hubuteam.ordersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-12
 * QQ：2311170320
 * 功能实现:
 */
@Controller
@RequestMapping("/Merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private DishService dishService;
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, Model model, String username, String password) {
        Merchant merchant = merchantService.merchantLogin(username, password);
        System.out.println("merchant测试02 "  + merchant);
        if (merchant != null) {
            session.setAttribute("merchant", merchant);
            return refreshView(model, merchant,"登陆成功");
        }else {
            model.addAttribute("msg", "用户名或密码错误，请重试。");
            return "error";
        }
    }

    @PostMapping("/deliverOrder")
    public String deliverOrder(HttpSession session, @RequestParam("orderId") int orderId, Model model) {
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        if (merchant == null) {
            return "redirect:/merchantLogin";
        }
        int n = orderService.updateStatusDelivery(orderId);
        if (n == 1) {
            return refreshView(model, merchant, "订单已标记为配送中！");
        } else {
            return refreshView(model, merchant, "标记订单为配送中失败！");
        }
    }

    @PostMapping("/acceptOrder")
    public String acceptOrder(HttpSession session, @RequestParam("orderId") int orderId, Model model) {
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        if (merchant == null) {
            return "redirect:/merchantLogin";
        }
        int n = orderService.updateStatusTaken(orderId);
        if (n == 1) {
            return refreshView(model, merchant, "订单已被接受！");
        } else {
            return refreshView(model, merchant, "接受订单失败！");
        }
    }

    @PostMapping("/completeOrder")
    public String completeOrder(HttpSession session, @RequestParam("orderId") int orderId, Model model) {
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        if (merchant == null) {
            return "redirect:/merchantLogin";
        }
        int n = orderService.updateStatusCompleted(orderId);
        if (n == 1) {
            return refreshView(model, merchant, "订单已标记为完成！");
        } else {
            return refreshView(model, merchant, "标记订单为完成失败！");
        }
    }

    @PostMapping("/updateDish")
    public String updateDish(HttpSession session, @RequestParam("dishId") long dishId, @RequestParam("dishName") String dishName,
                             @RequestParam("description") String description, @RequestParam("price") double price,
                             @RequestParam("imageUrl") MultipartFile imageUrl, Model model) {
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        if (merchant == null) {
            return "redirect:/merchantLogin";
        }
        Dish dish = new Dish();
        if (!imageUrl.isEmpty()) {
            // 上传图片到指定路径，并获取图片URL
            String fileName = imageUrl.getOriginalFilename();
            // 获取项目的根目录
            String projectDir = System.getProperty("user.dir");
            // 定义上传路径
            String uploadDir = projectDir + "/src/main/resources/static/images/dishs/";

            File uploadFile = new File(uploadDir, fileName);
            try {
                String imageUrlPath = null;
                imageUrl.transferTo(uploadFile);
                imageUrlPath = "/images/dishs/" + fileName;
                dish.setImageUrl(imageUrlPath);
            } catch (IOException e) {
                e.printStackTrace();
                return refreshView(model, merchant, "上传图片失败！");
            }
        }
        dish.setPrice(price);
        dish.setDishName(dishName);
        dish.setDescription(description);
        dish.setMerchantId(1);
        dish.setDishId(dishId);
        int n = dishService.updateDish(dish);
        if (n == 1) {
            return refreshView(model, merchant, "菜品信息更新成功！");
        } else {
            return refreshView(model, merchant, "菜品信息更新失败！");
        }
    }

    @PostMapping("/deleteDish")
    public String deleteDish(HttpSession session, @RequestParam("dishId") long dishId, Model model) {
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        if (merchant == null) {
            return "redirect:/merchantLogin";
        }
        int n = 0;
        try {
            n = dishService.deleteDish((int)dishId);
        } catch (Exception e) {
            return refreshView(model, merchant, "菜品删除失败！有外键依赖！");
        }
        if (n == 1) {
            return refreshView(model, merchant, "菜品删除成功！");
        } else {
            return refreshView(model, merchant, "菜品删除失败！");
        }
    }

    @PostMapping("/uploadDish")
    public String uploadDish(HttpSession session, @RequestParam("dishName") String dishName,
                             @RequestParam("description") String description, @RequestParam("price") double price,@RequestParam("merchantId") int merchantId,
                             @RequestParam("imageUrl") MultipartFile imageUrl, Model model) {
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        if (merchant == null) {
            return "redirect:/merchantLogin";
        }
        String imageUrlPath = null;
        if (!imageUrl.isEmpty()) {
            // 上传图片到指定路径，并获取图片URL
            String fileName = imageUrl.getOriginalFilename();
            // 获取项目的根目录
            String projectDir = System.getProperty("user.dir");
            // 定义上传路径
            String uploadDir = projectDir + "/src/main/resources/static/images/dishs/";
            File uploadFile = new File(uploadDir, fileName);
            try {
                imageUrl.transferTo(uploadFile);
                imageUrlPath = "/images/dishs/" + fileName;
            } catch (IOException e) {
                e.printStackTrace();
                return refreshView(model, merchant, "上传图片失败！");
            }
        }
        int n = dishService.insertDish(merchantId,dishName, description, price, imageUrlPath);
        if (n == 1) {
            return refreshView(model, merchant, "菜品上传成功！");
        } else {
            return refreshView(model, merchant, "菜品上传失败！");
        }
    }

    private String refreshView(Model model, Merchant merchant, String msg) {
        model.addAttribute("dishes", dishService.findAllDishs());
        List<Order> orders = merchantService.getAllOrders(merchant.getMerchantId());
        orders.forEach(order -> {
            order.setOrderDetails(orderService.orderDetailsByOrderIdService(order.getOrderId()));
            order.setReview(orderService.reviewByOrderIdService(order.getOrderId()));
        });
        model.addAttribute("orders", orders);
        model.addAttribute("msg",msg);
        return "merchantView";
    }

}
