package com.prospring5.repository;

public class CustomerRepository {
	public String fetchCustomer(int count) {
		return " The no of customers fetched are: " + count;
	}
	
	public String createCustomer() {
		return "Customer is succssfully created";
	}
}
