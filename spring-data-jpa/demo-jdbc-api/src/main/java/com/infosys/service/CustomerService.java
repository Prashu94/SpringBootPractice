package com.infosys.service;

import java.util.List;

import com.infosys.dto.CustomerDTO;

public interface CustomerService {
	// Method to access the repository layer method to insert customer record
    public void insert(CustomerDTO customer);
    // Method to access the repository layer method to delete customer record
    public int remove(Long phoneNo);
    // Method to get all the customer records
    public List<CustomerDTO> findAll();
    // Method to get the customer record by phoneNumber
    public CustomerDTO findByPhoneNumber(Long phoneNumber);
}
