package com.infosys.repository;

import java.util.List;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;

public interface CustomerDAO {
    // Method to insert a Customer record into the db
    public void insert(Customer customer);
    // Method to remove a Customer record from the db
    public int remove(Long phoneNo);
    // Find all the customers in the database
    public List<CustomerDTO> findAll();
    // Find the customer as per Phone Number
    public CustomerDTO findByPhoneNumber(Long phoneNumber);
    
}
