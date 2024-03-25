package com.musala.drones.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

//    @Before("execution(* com.musala.drones.serviceImpl.*.*(..))")
//    public void beforeMethodExecution() {
//        System.out.println("Before method execution: Logging the method");
//    }
//
    @After("execution(* com.musala.drones.serviceImpl.*.*(..))")
    public void afterMethodExecution(JoinPoint joinPoint) {
        System.out.println("After method execution: "+ Arrays.toString(joinPoint.getArgs()) +" Logging the method");
    }

    @Around("@annotation(com.musala.drones.annotations.LoggerAspect)")
    public Object aroundMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before method execution: Logging the method");
        Object result = joinPoint.proceed();
        System.out.println("After method execution: Logging the method");
        return result;
    }
}
