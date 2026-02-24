package com.example.Product_Service.aop;

import com.example.Product_Service.repository.ProductRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    private static final Logger logger =
            LoggerFactory.getLogger(PerformanceAspect.class);
    @Around("execution(* com.example.Product_Service.service.*.*(..))")
    public Object MeasureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long end = System.currentTimeMillis();
        logger.info("Method {} executed in {} ms" , joinPoint.getSignature().getName(), end - start);
        return result;
    }
}
