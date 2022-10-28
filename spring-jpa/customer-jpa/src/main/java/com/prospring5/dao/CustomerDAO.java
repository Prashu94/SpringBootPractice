package com.prospring5.dao;

import java.util.List;

import com.prospring5.entity.Customer;

public interface CustomerDAO {
	// Method to insert a customer record into the db
	public void insert(Customer customer);
	// Method to remove Customer record from the db
	public int remove(Long phoneNo);
	// Method to get all the customer record from the db
	public List<Customer> getAll();
	// Method to update a Customer record from the db
	public void update(Long phoneNo, String address);
}
