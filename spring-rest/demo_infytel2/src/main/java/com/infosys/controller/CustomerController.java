package com.infosys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.dto.CustomerDTO;
import com.infosys.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	// CustomerController needs to contact CustomerService, hence dependency injecting the 
	// customerService reference
	@Autowired
	private CustomerService customerService;
	
	// Fetch the customerDetails
	@GetMapping(produces = "application/json")
	public List<CustomerDTO> fetchCustomer(){
		// This method will return the customers of InfyTel
		return customerService.fetchCustomer();
	}
	
	// Adding a customer
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO){
		// This method will create a customer
		String response = customerService.createCustomer(customerDTO);
		return ResponseEntity.ok(response);
	}
	
	// Updating an existing customer
	@PutMapping
	public String updateCustomer() {
		// This method will update an existing customer
		return "customer details updated successfully";
	}
	
	// Delete mapping
	@DeleteMapping
	public String deleteCustomer() {
		// This method will delete an existing customer
		return "customer details deleted successfully";
	}
	
	// update an existing customer
	@PutMapping(value = "/{phoneNumber}", consumes = "application/json")
	public String updateCustomer(@PathVariable("phoneNumber") long phoneNumber, CustomerDTO customerDTO) {
		return customerService.updateCustomer(phoneNumber, customerDTO);
	}
	
	// Deleting the customer
	@DeleteMapping("/{phoneNumber}")
	public String deleteCustomer(@PathVariable("phoneNumber") long phoneNumber) {
		return customerService.deleteCustomer(phoneNumber);
	}
}
