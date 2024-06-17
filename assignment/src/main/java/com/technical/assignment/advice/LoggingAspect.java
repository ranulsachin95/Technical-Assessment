package com.technical.assignment.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.technical.assignment.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Executing method: {}", joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("Argument: {}", arg);
        }
    }

    @AfterReturning(pointcut = "execution(* com.technical.assignment.controller..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method executed: {}", joinPoint.getSignature().toShortString());
        if (result instanceof ResponseEntity<?>) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            log.info("Response: {}", responseEntity.getBody());
        } else {
            log.info("Response: {}", result);
        }
    }

    @AfterThrowing(pointcut = "execution(* com.technical.assignment.controller..*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("Exception in method: {}", joinPoint.getSignature().toShortString(), error);
    }
}