package org.example.service.impl;

import org.example.aop.InvokeLog;
import org.example.domain.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @InvokeLog
    public List<User> findAll() {
        return userMapper.findAll();
    }

    // 使用声明式注解使得该函数作为一个事物，也就是出现异常时需要将所有执行过的sql回滚
    // 声明式事务依赖在mybatis中已经被添加，无需重新添加依赖
    // 声明式事务使用 @Transactional
    @Override
    @Transactional
    @InvokeLog
    public void insertUser() {
        // 添加两个用户到user表中
        User user = new User(null, "食发鬼", "789789");
        User user2 = new User(null, "efd", "787");
        userMapper.insertUser(user);
        // 模拟出现异常
        // System.out.println(1/0);
        userMapper.insertUser(user2);
    }
}
