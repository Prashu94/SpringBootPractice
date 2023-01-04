package com.infytel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infytel.dto.CustomerDTO;
import com.infytel.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController 
{
	
	//CustomerController needs to contact CustomerService, hence dependency injecting the customerService reference
	@Autowired
	private CustomerService customerService;
	
	//Fetching customer details
	@GetMapping(produces="application/json")
	public  List<CustomerDTO> fetchCustomer()
	{
		//This method will return the customers of Infytel
		return customerService.fetchCustomer();
	}
		
	//Adding a customer
	@PostMapping(consumes="application/json")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO)
	{
		//This method will create a customer
		String response = customerService.createCustomer(customerDTO);
		return ResponseEntity.ok(response);
	}

	//Updating an existing customer
	@PutMapping
	public String updateCustomer() 
	{
		//This method will update an existing customer 
		return "customer details updated successfully";
	}
	
	//Deleting a customer
	@DeleteMapping
	public String deleteCustomer() 
	{
		//This method will delete a customer 
		return "customer details deleted successfully";
	}
}
