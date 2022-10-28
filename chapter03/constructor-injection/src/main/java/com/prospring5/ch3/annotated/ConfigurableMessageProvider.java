package com.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospring5.ch2.decoupled.MessageProvider;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider{
	private String message;
	
	@Autowired
	public ConfigurableMessageProvider(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		
		return this.message;
	}

}
