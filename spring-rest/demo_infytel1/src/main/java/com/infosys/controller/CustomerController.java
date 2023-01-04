package com.infosys.controller;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	// Fetching the customer details
	@GetMapping
	public String fetchCustomer() {
		
		return "Customers fetched successfully";
	}
	
	
	// Adding a new customer
	@PostMapping
	public String createCustomer() {
		
		return "Customers added successfully";
	}
	
	// Updating an existing customer
	@PutMapping
	public String updateCustoemr() {
		
		return "Customers updated successfully";
	}
	
	// Deleting a customer
	@DeleteMapping
	public String deleteCustomer() {
		
		return "Customer details deleted successfully";
	}
	
	// Exercise Solution
	@GetMapping(path = "/greet")
	public String greetCustomer() {
		
		return "Welcome to "+ LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US) + " sale";
	}
}
