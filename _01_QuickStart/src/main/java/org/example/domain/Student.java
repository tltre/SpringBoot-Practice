package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/*
* 使用@ConfigurationProperties时，需要指明前缀
* 同时，类中要提供get/set方法
* 需要将该类使用@Component声明为容器的一部分
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "student")
public class Student {
    private String name;
    private Integer age;
}
