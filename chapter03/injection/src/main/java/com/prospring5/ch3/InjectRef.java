package com.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectRef {
	private Oracle oracle;
	
	public void setOracle(Oracle oracle) {
		this.oracle = oracle;
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-xml.xml");
		ctx.refresh();
		
		
		InjectRef inject = (InjectRef)ctx.getBean("injectRef");
		System.out.println(inject);
		ctx.close();
	}
	
	public String toString() {
        return oracle.defineMeaningOfLife();
    }
}
