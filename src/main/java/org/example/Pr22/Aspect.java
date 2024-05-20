package org.example.Pr22;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    @Before("allServiceMethods()")
    public void logParameters(JoinPoint joinPoint) {
        log.info("Parameters: {}", joinPoint.getArgs());
    }
    @Pointcut("within(org.example.Pr20.services.*)")
    public void allServiceMethods() {

    }
    @Around("execution(* *(..)) && within(org.example.Pr20.services.*)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        try {
            return joinPoint.proceed();

        } finally {
            log.info(joinPoint + " -> " + (System.nanoTime() - startTime) / 1000000 + " ms");
        }
    }
}
