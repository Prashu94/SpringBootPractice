package com.prospring5.util;

import java.text.DateFormat;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.prospring5.dto.CustomerDTO;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	// Method to Log after throwing exception in advice joinpoint
	@AfterThrowing("execution(* com.prospring5.service.CustomerServiceImpl.deleteCustomer(..))")
	public void logAfterThrowingAdvice(JoinPoint joinPoint) {
		logger.info("In After Throwing Advice, JoinPoint Signature : {}", joinPoint.getSignature());
	}
	
	
	//@AfterThrowing(pointcut = "execution(* com.prospring5.service.CustomerServiceImpl.deleteCustomer(..))", throwing = "exception")
	public void logAfterThrowingAdviceDetails(JoinPoint joinPoint, Exception e) {
		logger.info("In After throwing Advice, Joinpoint signature: {}", joinPoint.getSignature());
		logger.error(e.getMessage(), e);
	}
	
	@After(value = "execution(* com.prospring5.service.CustomerServiceImpl.fetchCustomer(..))")
	// After advice will be executed after the execution of Joinpoint
	// whether it returns with or without exception. Similar to finally block in exception handling.
	public void logAfterAdvice(JoinPoint joinPoint) {
		logger.info("In After Advice, Joinpoint Signature: {}", joinPoint.getSignature());
		long time = System.currentTimeMillis();
		String date = DateFormat.getDateTimeInstance().format(time);
		logger.info("Report generated at time : {}", date);
	}
	
	@Before(value = "execution(* com.prospring5.service.CustomerServiceImpl.fetchCustomer(..))")
	//Before advice is executed before the Joinpoint execution.
	public void logBeforeAdvice(JoinPoint joinPoint) {
		logger.info("In Before Advice, JoinPoint Signature: {}", joinPoint.getSignature());
		long time = System.currentTimeMillis();
		String date = DateFormat.getDateTimeInstance().format(time);
		logger.info("Report Generated at time: {}", date);
	}
	
	//@AfterReturning(pointcut = "execution(*com.prospring5.service.CustomerServiceImpl.fetchCustomer(..))")
	// AfterReturning advice is executed after a Joinpoint executes and returns successfully without exceptions
	public void logAfterReturningAdvice(JoinPoint joinPoint, List<CustomerDTO> result) {
		logger.info("In AfterReturning Advice, JoinPoint Signature : {}", joinPoint.getSignature());
	}
	
	//@AfterReturning(pointcut = "execution(*com.prospring5.service.CustomerServiceImpl.fetchCustomer(..))", returning = "result")
	// After returning advice is executed after a JoinPoint executes and we can access the returning attribute of @AfterReturning 
	// annotations
	public void logAfterReturningDetails(JoinPoint joinPoint, List<CustomerDTO> result) {
		logger.info("In AfterReturning Advice with return value, JoinPoint Signature: {}", joinPoint.getSignature());
		System.out.println(result);
		long time = System.currentTimeMillis();
		String date = DateFormat.getDateTimeInstance().format(time);
		logger.info("Report Generated at Time: {}", date);
	}
	
	// Around advice is executed around the join points which means AroundAdvice 
	// has some logic which gets executed before JoinPoint invocation and some logic
	// which gets executed after the JoinPoint returns successfully.
	//@Around("execution(*com.prospring5.service.CustomerServiceImpl.fetchCustomer(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("Before proceeding part of the Around Advice");
		Object cust = joinPoint.proceed();
		System.out.println("After proceeding part of the Around Advice");
		return cust;
	}
	
	
	
}
