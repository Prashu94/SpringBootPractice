package com.infosys.exception;

public class InfyTransactionException extends Exception{
	private static final Long serialVersionUID = 1L;
	
	public InfyTransactionException(String message) {
		super(message);
	}
}
