package com.example.Product_Service.aop;
//import com.example.Product_Service.ProductService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component public class ExceptionAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);


    @AfterThrowing(
            pointcut = "execution(* com.example.Product_Service.service.*.*(..))",
            throwing = "ex")
    public void logException(ProceedingJoinPoint joinPoint ,Throwable ex)   {
        logger.error("Exception Occurred: {}",joinPoint.getSignature());
    }
}
