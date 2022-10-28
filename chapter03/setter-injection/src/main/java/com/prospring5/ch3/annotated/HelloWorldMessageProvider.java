package com.prospring5.ch3.annotated;

import org.springframework.stereotype.Component;

import com.prospring5.ch2.decoupled.MessageProvider;

@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider{

	@Override
	public String getMessage() {
		return "Hello World";
	}

}
