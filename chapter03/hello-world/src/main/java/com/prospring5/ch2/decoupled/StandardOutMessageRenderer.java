package com.prospring5.ch2.decoupled;

public class StandardOutMessageRenderer implements MessageRenderer{
	private MessageProvider messageProvider;
	
	public StandardOutMessageRenderer() {
		System.out.println("--> StandardOutMessageRenderer: constructor called");
	}
	public void render() {
		if(messageProvider == null) {
			throw new RuntimeException(
					"You must set the property messageProvider of class:"
							+ StandardOutMessageRenderer.class.getName());
		}
		System.out.println(messageProvider.getMessage());
	}

	public void setMessageProvider(MessageProvider provider) {
		System.out.println("--> StandardOutMessageRenderer: setting the provider variable");
		this.messageProvider = provider;
	}

	public MessageProvider getMessageProvider() {
		return this.messageProvider;
	}

}
