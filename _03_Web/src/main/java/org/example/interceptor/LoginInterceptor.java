package org.example.interceptor;

import io.jsonwebtoken.Claims;
import org.example.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("token");
        // 判断token是否为空
        if(!StringUtils.hasText(token))
        {
            // token为空，需要重新登录
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException("未登录，请重新登录");
        }
        // 解析token
        try {
            Claims claims = JwtUtil.parseJWT(token);
            String subject = claims.getSubject();
            System.out.println(subject);
        } catch (Exception e) {
            e.printStackTrace();
            // 出现异常则返回401提醒重新登录
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException("未登录，请重新登录");
        }
        // 若无异常抛出则成功可放行
        return true;
    }
}
