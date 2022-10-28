package com.prospring5.ch3;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.prospring5.ch2.decoupled.MessageProvider;

public class DeclareSpringComponents {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();
		
		
		MessageProvider messageProvider = ctx.getBean("provider", MessageProvider.class);
		ctx.close();
	}
}
