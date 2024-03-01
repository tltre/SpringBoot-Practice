package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 跨域解决方法2： 实现 WebMvcConfigurer 接口，并声明为 @Configuration
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许请求跨域的路径
        registry.addMapping("/**")
                // 设置允许请求跨域的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 允许的http方法
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 允许的请求头
                .allowedHeaders("content-type", "token")
                // 跨域允许时间
                .maxAge(3600);
    }
}
