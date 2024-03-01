package org.example.resolver;

import io.jsonwebtoken.Claims;
import org.example.utils.JwtUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;

// 另一种参数解析方法，实现 HandlerMethodArgumentResolver 接口
// 比起在每个handle函数中都使用一段重复性极高的代码通过获取request对象进行参数解析（见UserController）
// 使用一个类统一处理更加便于维护

// 注入Spring容器
@Component
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    // 判断方法参数能否使用当前参数解析器进行解析
    // 若可以，返回true
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // 若方法参数有加上 @CurrentUserId 注解，则可以解析
        return methodParameter.hasParameterAnnotation(CurrentUserId.class);
    }

    // 参数解析：获取数据并返回，将返回数据赋值给方法中的形式参数
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 获取请求头token
        String token = nativeWebRequest.getHeader("token");
        if(StringUtils.hasText(token))
        {
            // 解析token获取用户id
            Claims claims = JwtUtil.parseJWT(token);
            String userId = claims.getSubject();
            return userId;
        }
        return null;
    }
}
