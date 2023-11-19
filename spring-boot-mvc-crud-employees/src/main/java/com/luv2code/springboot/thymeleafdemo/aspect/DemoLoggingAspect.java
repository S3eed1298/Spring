package com.luv2code.springboot.thymeleafdemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackages(){}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("forControllerPackages() || forDaoPackage() || forServicePackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        // display the method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("======>> in @Before: calling method: " + theMethod);

        // display the args of the method
        Object[] args = joinPoint.getArgs();

        for (Object arg : args){
            logger.info("======>> argument: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void After(JoinPoint joinPoint, Object theResult){
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("======>> in @AfterReturning: calling method: " + theMethod);

        logger.info("=======>> Result: " + theResult);
    }
}
