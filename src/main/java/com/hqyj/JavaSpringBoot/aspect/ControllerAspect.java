package com.hqyj.JavaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {
     //自定义Controller切面方法
    @Pointcut("execution(public * com.hqyj.JavaSpringBoot.modules.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut() {}

    @Before(value = "com.hqyj.JavaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint){
//        System.out.println("Im beforeController方法");
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attributes.getRequest();
//        System.out.println("请求来源："+request.getRemoteAddr());
        System.out.println("请求URL："+request.getRequestURL().toString());
        System.out.println("请求方式："+request.getMethod());
//        System.out.println("响应方法："+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
//        System.out.println("请求参数："+ Arrays.toString(joinPoint.getArgs()));
    }

    @Around(value = "com.hqyj.JavaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
//        System.out.println("Im aroundController方法");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.hqyj.JavaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void afterController() {
//        System.out.println("Im afterController方法");
    }
}
