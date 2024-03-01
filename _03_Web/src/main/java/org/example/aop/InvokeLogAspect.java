package org.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

// 切面类，可批量增强一些类方法
@Aspect
@Component
public class InvokeLogAspect {

    // 确定切面类增强的方法类型
    // 待增强的方法使用注解进行标识
    // 示例：在UsrServiceImpl的findAll方法上添加注解
    @Pointcut("@annotation(org.example.aop.InvokeLog)")
    public void pt(){
    }

    // 通知方法
    // 在注解中标识响应哪个切点（也就是带有注解 PointCut 的类方法）
    @Around("pt()")
    public Object printInvokeLog(ProceedingJoinPoint pjp){
        // ProceedingJoinPoint 代表正在运行中的被增强方法
        Object proceed = null;
        
        // 目标方法调用前
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getMethod().getName();
        System.out.println(methodName + "即将被调用");

        try {
            // 调用被增强方法
            proceed = pjp.proceed();
            // 目标方法调用后
            System.out.println(methodName + "调用完成");
        } catch (Throwable e) {
            // 目标方法出现异常
            System.out.println(methodName + "调用时出现异常");
            throw new RuntimeException(e);
        }

        return proceed;
    }
}
