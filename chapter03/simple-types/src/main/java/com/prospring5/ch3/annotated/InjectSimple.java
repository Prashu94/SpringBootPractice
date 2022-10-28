package com.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class InjectSimple {
	
	@Value("John Mayer")
	private String name;
	@Value("40")
	private int age;
	@Value("1.42")
	private float height;
	@Value("false")
	private boolean programmer;
	@Value("1241401112")
	private Long ageInSeconds;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();
		
		
		InjectSimple injectSimple = (InjectSimple)ctx.getBean("injectSimple");
		System.out.println(injectSimple);
		
		ctx.close();
	}

	@Override
	public String toString() {
		return "InjectSimple [name=" + name + ", age=" + age + ", height=" + height + ", programmer=" + programmer
				+ ", ageInSeconds=" + ageInSeconds + "]";
	}
	
	
}
