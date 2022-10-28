package com.prospring5.ch2.decoupled;

public class HelloWorldMessageProvider implements MessageProvider{
	
	public HelloWorldMessageProvider() {
		System.out.println("--> HelloWorldMessageProvider: constructor called");
	}
	public String getMessage() {
		return "Hello World";
	}
	
}
