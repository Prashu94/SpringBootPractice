package com.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacementDemo {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-xml.xml");
		ctx.refresh();
		
		ReplacementTarget replacementTarget = (ReplacementTarget)ctx.getBean("replacementTarget");
		ReplacementTarget standardTarget = (ReplacementTarget)ctx.getBean("standardTarget");
		
		displayInfo(replacementTarget);
		displayInfo(standardTarget);
		
		ctx.close();
	}
	
	private static void displayInfo(ReplacementTarget target) {
		System.out.println(target.formatMessage("Thanks for playing, try again!"));
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start("perfTest");
		
		
		for(int x = 0; x<1000000; x++) {
			String out = target.formatMessage("No Filter in my head");
		}
		
		stopwatch.stop();
		System.out.println("1000000 invocations took :" + stopwatch.getTotalTimeMillis() + " ms");
	}
}