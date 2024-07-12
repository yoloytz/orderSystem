package com.hubuteam.ordersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现: 公共视图服务器请求跳转
 */
@Controller
public class ViewController {
    /**
     * 处理根路径的请求
     * @return 返回应用首页
     */
    @RequestMapping("/")
    public String test(HttpSession session){
        try {
            session.removeAttribute("user");
        } catch (Exception e) {
            System.err.println("Error remove user session");
        }
        return "index";
    }

    @RequestMapping("/hello")
    public String index(){
        return "index";
    }


}
