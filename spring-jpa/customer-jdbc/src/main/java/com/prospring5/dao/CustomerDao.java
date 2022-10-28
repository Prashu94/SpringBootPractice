package com.prospring5.dao;

import java.util.List;

import com.prospring5.entities.Customer;

public interface CustomerDao {
	
	List<Customer> findAll();
	
	void insert(Customer customer);
	
	void update(Customer customer);
	
	void delete(Long phonNumber);
}
