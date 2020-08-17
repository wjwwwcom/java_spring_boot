package com.hqyj.JavaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
     //定义切面方法
    @Pointcut("@annotation(com.hqyj.JavaSpringBoot.aspect.ServiceAnnotation)")
    @Order(2)
   public void servicePointCut(){};

    @Before(value = "com.hqyj.JavaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinpoint) {
        System.out.println("Im beforeService方法");
    }

    @Around(value = "com.hqyj.JavaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        System.out.println("Im aroundService方法");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.hqyj.JavaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void afterService() {
        System.out.println("Im afterService方法");
    }

}
