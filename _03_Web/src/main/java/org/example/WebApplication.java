package org.example;

import org.example.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        // 返回值是spring容器对象
        ConfigurableApplicationContext context = SpringApplication.run(WebApplication.class, args);
        System.out.println(context.getBean(UserService.class).getClass().getName());
    }
}
