package org.example.controller;

import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ThymeLeafController {

    @Autowired
    private UserService userService;

    @RequestMapping("/thymeleaf/users")
    public String users(Model model){
        // 获取数据
        List<User> users = userService.findAll();

        // 往域中存放数据
        model.addAttribute("users", users);
        model.addAttribute("msg", "helloThymeLeaf");

        // 页面跳转
        // 静态资源存放在 templates 文件夹下
        // 自动配置好前后缀
        return "bs-basic-table";
    }
}
