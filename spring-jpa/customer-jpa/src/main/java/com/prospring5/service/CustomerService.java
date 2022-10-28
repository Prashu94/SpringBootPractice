package com.prospring5.service;

import java.util.List;

import com.prospring5.dto.CustomerDTO;

public interface CustomerService {
	// Method to access the repository layer method to insert Customer needed
	public void insert(CustomerDTO customer);
	// Method to access the repository layer method to delete the customer record
	public int remove(Long phoneNo);
	// Method to get all the customer record from the db
	public List<CustomerDTO> geAll();
	// Method to update a customer record from the db
	public void update(Long phoneNo, String address);
}
