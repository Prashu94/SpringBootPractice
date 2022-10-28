package com.prospring5.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(123)
public class MyApiAnalyticsAspect {
	
	@Before("com.prospring5.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void performApiAnaytics() {
		System.out.println("\n=====>>> Performing API Analytics");
	}
}
