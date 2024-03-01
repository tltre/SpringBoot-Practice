package org.example.controller;

import io.jsonwebtoken.Claims;
import org.example.domain.ResponseResult;
import org.example.domain.User;
import org.example.resolver.CurrentUserId;
import org.example.service.UserService;
import org.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/insertUser")
    public ResponseResult insertUser(){
        userService.insertUser();
        return new ResponseResult<>(200, "添加成功");
    }


    // 跨域解决方法1： @CrossOrigin
    // @CrossOrigin也可以加在类上
    @RequestMapping("/findAll")
    // @CrossOrigin
    public ResponseResult findAll(@CurrentUserId String userId) throws Exception {
        // 一种获取请求头参数的方法：使用HttpServletRequest
        /*
            // 添加形式参数：HttpServletRequest request
            // 获取请求头token
            String token = request.getHeader("token");
            if(token != null)
            {
                // 解析token获取用户id
                Claims claims = JwtUtil.parseJWT(token);
                String userId = claims.getSubject();
                System.out.println(userId);
            }
        */
        System.out.println("userId = " + userId);

        List<User> users = userService.findAll();
        return new ResponseResult<>(200, users);
    }
}
