package org.example.controller;

import org.example.domain.ResponseResult;
import org.example.domain.SystemUser;
import org.example.service.SystemUserService;
import org.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/sys_user")
public class SystemUserController {

    @Autowired
    private SystemUserService service;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody SystemUser user){
        // 校验用户名密码是否正确
        SystemUser loginUser = service.login(user);
        Map<String, Object> map;
        if(loginUser != null)
        {
            // 若正确，生成token返回
            map = new HashMap<>();
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(loginUser.getId()), null);
            map.put("token", token);
        }
        else{
            // 反之，给出提示信息
            return new ResponseResult<>(300, "用户名或密码有误，请重新登录");
        }
        return new ResponseResult<>(200, "登录成功", map);

    }
}
