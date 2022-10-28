package com.prospring5.ch3.xml;

import com.prospring5.ch2.decoupled.MessageProvider;

public class ConfigurableMessageProvider implements MessageProvider {
	private String message = "Default Message";
	
	public ConfigurableMessageProvider() {}
	
	public ConfigurableMessageProvider(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	
}
