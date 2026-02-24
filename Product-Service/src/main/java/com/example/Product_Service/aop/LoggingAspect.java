package com.example.Product_Service.aop;

import com.example.Product_Service.entity.ProductEntity;
import com.example.Product_Service.service.ProductService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before( "execution(* com.example.Product_Service.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint)throws Throwable {
        logger.info("Calling Method {} ",joinPoint.getSignature().getName());
    }
}
