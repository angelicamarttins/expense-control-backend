package com.angelicamartins.expensecontrol.advice;

import java.time.Duration;
import java.time.ZonedDateTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAdvice {

  @Before("execution(* com.angelicamartins.expensecontrol.controller.CategoryController.*(..))")
  public void beforeAnyExecution() {
    System.out.println("Before any execution in CategoryController");
  }

  @After("execution(* com.angelicamartins.expensecontrol.controller.CategoryController.*(..))")
  public void afterAnyExecution() {
    System.out.println("After any execution in CategoryController");
  }

  @AfterReturning("execution(* com.angelicamartins.expensecontrol.controller.CategoryController.*(..))")
  public void afterReturningAnyExecution() {
    System.out.println("After returning any execution in CategoryController");
  }

  @Around("execution(* com.angelicamartins.expensecontrol.controller.CategoryController.*(..))")
  public Object aroundAnyExecution(ProceedingJoinPoint pjp) {
    System.out.println("---START--- Around any execution in CategoryController");
    ZonedDateTime startRequest = ZonedDateTime.now();
    try {
      Object returnObject = pjp.proceed();

      System.out.println("---END--- Around any execution in CategoryController");

      ZonedDateTime endRequest = ZonedDateTime.now();
      System.out.println("Requesting process duration: "
                           + Duration.between(startRequest.toInstant(), endRequest.toInstant()).toMillis()
                           + "ms");
      return returnObject;
    } catch (Throwable throwable) {
      System.out.println("---END--- Around any execution in CategoryController. Something happen: " + throwable);
    }

    return null;
  }

}
