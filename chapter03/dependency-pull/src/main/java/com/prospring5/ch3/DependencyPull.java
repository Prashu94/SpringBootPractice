package com.prospring5.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.prospring5.ch3.decoupled.MessageRenderer;

public class DependencyPull {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/app-context.xml");
		
		MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
		mr.render();
	}
}
