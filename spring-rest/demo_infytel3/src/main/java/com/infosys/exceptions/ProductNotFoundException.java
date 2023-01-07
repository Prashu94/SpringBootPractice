package com.infosys.exceptions;

public class ProductNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException() {
		super();
	}
	
	public ProductNotFoundException(String errors) {
		super(errors);
	}
}
