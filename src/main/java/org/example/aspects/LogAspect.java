package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LogAspect {

    Logger logger = Logger.getLogger(LogAspect.class.getName());

    @Around("execution(* org.example.services.*.*(..))")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println();
        logger.info(proceedingJoinPoint.getSignature().getName()+" method will execute");
        Object result = proceedingJoinPoint.proceed();
        logger.info("method executed");
        return result;
    }
}

