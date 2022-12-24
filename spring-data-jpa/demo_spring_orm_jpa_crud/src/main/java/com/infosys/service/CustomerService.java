package com.infosys.service;

import java.util.List;

import com.infosys.dto.CustomerDTO;

public interface CustomerService {
	// Method to access the repository layer method to insert Customer record
	public void insert(CustomerDTO customer);
	// Method to access the repository layer method to delete the Customer record
	public int remove(Long phoneNo);
	// Method to get all the Customer record from the db
	public List<CustomerDTO> getAll();
	// Method to update Customer record from the db.
	public void update(Long phoneNo, String address);
}
