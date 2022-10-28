package com.prospring5.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConstructorConfusion {
	
	private String someValue;
	
	public ConstructorConfusion(String someValue) {
		System.out.println("ConstructorConfusion(String) called");
		this.someValue = someValue;
	}
	
	public ConstructorConfusion(int someValue) {
		System.out.println("ConstructorConfusion(int) called");
		this.someValue = "Number: "+ Integer.toString(someValue);
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context.xml");
		ctx.refresh();
		ConstructorConfusion cc = (ConstructorConfusion) ctx.getBean("constructorConfusion");
		System.out.println(cc);
		ConstructorConfusion cc1 = (ConstructorConfusion) ctx.getBean("constructorConfusion1");
		System.out.println(cc1);
		ctx.close();
	}
}
