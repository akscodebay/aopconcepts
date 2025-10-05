package org.aop.aopconcepts.crosscuttingconcerns;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* org.aop.aopconcepts.restcontroller.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before Method: "+ joinPoint.getSignature().getName());
    }

    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After Method: "+ joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint,  Object result) {
        System.out.println("After returning from "+ joinPoint.getSignature().getName() + " result: " + result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint,  Throwable error) {
        System.out.println("After Throwing: "+ joinPoint.getSignature().getName() + " Error: "+ error);
    }

    @Around("execution(* org.aop.aopconcepts.restcontroller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        System.out.println("Before executing: " + joinPoint.getSignature().getName());

        // Execute actual method
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println("After executing: " + joinPoint.getSignature().getName() +
                " | Execution time: " + (endTime - startTime) + " ms");

        return result;
    }
}
