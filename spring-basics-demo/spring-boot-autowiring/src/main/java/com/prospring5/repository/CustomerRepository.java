package com.prospring5.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.prospring5.dto.CustomerDTO;

@Repository
public class CustomerRepository {
	List<CustomerDTO> customers = null;
	
	@PostConstruct
	public void initializer() {
		CustomerDTO customer = new CustomerDTO();
		customer.setAddress("Chennai");
		customer.setName("Jack");
		customer.setEmail("Jack@infy.com");
		customer.setPhoneNo(9951212222l);
		customers = new ArrayList<>();
		customers.add(customer);
	}
	
	// adds the received customer object to customers list
	public void createCustomer(CustomerDTO customerDTO) {
		customers.add(customerDTO);
	}
	
	// returns a list of customers
	public List<CustomerDTO> fetchCustomer(){
		return customers;
	}
}
