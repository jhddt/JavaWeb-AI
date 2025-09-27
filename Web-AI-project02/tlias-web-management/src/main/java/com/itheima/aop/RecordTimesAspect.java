package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect // 声明当前类为切面类
@Component
public class RecordTimesAspect {
    @Around("execution(* com.itheima.service.impl.*.*(..))") // 定义切点，环绕通知
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("方法{}耗时：{}ms",pjp.getSignature(),end-start);// pjp.getSignature()获取方法的签名
        return result;
    }
}
