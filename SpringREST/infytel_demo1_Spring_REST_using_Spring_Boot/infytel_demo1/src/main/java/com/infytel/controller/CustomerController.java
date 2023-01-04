package com.infytel.controller;
 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController 
{
	
	//Fetching the customer details
	@GetMapping
	public String fetchCustomer()
	{
		//This method will fetch the customers of Infytel and return the same. 
		return "customers fetched successfully";
	}
		
	//Adding a new customer
	@PostMapping
	public String createCustomer() 
	{
		//This method will persist the details of a customer
		return "Customer added successfully";
	}

	
	//Updating an existing customer
	@PutMapping
	public String updateCustomer() 
	{
		//This method will update the details of an existing customer 
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
