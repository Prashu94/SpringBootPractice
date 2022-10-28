package com.prospring5.ch3.mixed;



import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prospring5.ch2.decoupled.MessageRenderer;

public class HelloWorldSpringMixed {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
		MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
		mr.render();
	}
}
