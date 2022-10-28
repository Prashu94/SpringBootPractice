package com.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("injectSimpleSepl")
public class InjectSimpleSepl {
	
	@Value("#{injectSimpleConfig.name}")
	private String name;
	@Value("#{injectSimpleConfig.age+1}")
	private int age;
	@Value("#{injectSimpleConfig.height}")
	private float height;
	@Value("#{injectSimpleConfig.programmer}")
	private boolean programmer;
	@Value("#{injectSimpleConfig.ageInSeconds}")
	private Long ageInSeconds;
	
	
	@Override
	public String toString() {
		return "InjectSimpleSepl [name=" + name + ", age=" + age + ", height=" + height + ", programmer=" + programmer
				+ ", ageInSeconds=" + ageInSeconds + "]";
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();
		
		
		InjectSimpleSepl injectSimple = (InjectSimpleSepl)ctx.getBean("injectSimpleSepl");
		
		System.out.println(injectSimple);
		ctx.close();
	}
	
}
