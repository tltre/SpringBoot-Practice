package org.example.controller;

import org.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RestController = @Controller + @ResponseBody
public class HelloController {

    // 使用@Value注解读取yml文件中简单变量（如Date、String、对象），较少用
    @Value("${key}")
    private String key;

    @Value("${student.age}")
    private Integer age;

    // 在Student类中使用@ConfigurationProperties赋值，就可以用@Autowired进行自动赋值
    @Autowired
    private Student student;

    @RequestMapping("/hello")
    public String hello(){
        return "HelloSpringBoot";
    }

    @RequestMapping("/test2")
    public String test(){
        System.out.println(age);
        System.out.println(student);
        return key;
    }
}
