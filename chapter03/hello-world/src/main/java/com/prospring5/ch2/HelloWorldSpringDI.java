package com.prospring5.ch2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.prospring5.ch2.decoupled.MessageRenderer;

public class HelloWorldSpringDI {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/app-context.xml");
		MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);
		mr.render();
	}
}
