package com.prospring5.ch2.annotated;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prospring5.ch2.decoupled.HelloWorldMessageProvider;
import com.prospring5.ch2.decoupled.MessageProvider;
import com.prospring5.ch2.decoupled.MessageRenderer;
import com.prospring5.ch2.decoupled.StandardOutMessageRenderer;

@Configuration
public class HelloWorldConfiguration {
	@Bean
	public MessageProvider provider() {
		return new HelloWorldMessageProvider();
	}
	
	@Bean
	public MessageRenderer renderer() {
		MessageRenderer renderer = new StandardOutMessageRenderer();
		renderer.setMessageProvider(provider());
		return renderer;
	}
}
