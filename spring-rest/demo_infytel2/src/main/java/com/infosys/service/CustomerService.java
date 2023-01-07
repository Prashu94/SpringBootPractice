package com.infosys.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.CustomerDTO;
import com.infosys.exceptions.NoSuchCustomerException;
import com.infosys.repository.CustomerRepository;

@Service
public class CustomerService {
	
	/**
	 * Injection of CustomerRepository to get the data from the repository.
	 */
	@Autowired
	private CustomerRepository customerRepository;
	
	// makes a call to repository method for adding the customer
	public String createCustomer(CustomerDTO customerDTO) {
		customerRepository.createCustomer(customerDTO);
		
		return "Customer with " + customerDTO.getPhoneNo() + " added successfully";
	}
	
	// makes a call to repository method for returning a list of customers
	public List<CustomerDTO> fetchCustomer(){
		List<CustomerDTO> customers = customerRepository.fetchCustomer();
		//code that iterates through customers and set the password to *
		return customers.stream().map(c->{c.setPassword("*");return c;}).collect(Collectors.toList());
	}
	
	/**
	 * Makes a call to a repository method for fetching customer list and
	 * updates the customer's details
	 */
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO) {
		String response = "Customer of : " + phoneNumber + " does not exist";
		
		for(CustomerDTO customer: customerRepository.fetchCustomer()) {
			if(customer.getPhoneNo() == phoneNumber) {
				if(customer.getName()!=null) {
					customer.setName(customerDTO.getName());
				}
				if(customer.getAddress()!=null) {
					customer.setAddress(customerDTO.getAddress());
				}
				if(customer.getPassword()!=null) {
					customer.setPassword(customerDTO.getPassword());
				}
				response = "Customer of phoneNumber "+customer.getPhoneNo()+" got updated successfully";
				break;
			}
		}
		return response;
	}
	
	/**
	 * Makes a call to a repository method for fetching the customers list and 
	 * then calls the repository's deleteCustomer() method with the customer to be deleted
	 */
	/*public String deleteCustomer(long phoneNumber) throws NoSuchCustomerException {
		String response = "Customer of: " + phoneNumber + " does not exist";
		
		for(CustomerDTO customer: customerRepository.fetchCustomer()) {
			if(customer.getPhoneNo() == phoneNumber) {
				customerRepository.deleteCustomer(customer);
				
				response = customer.getName() + " of phoneNumber " + customer.getPhoneNo()
				+ " got deleted successfully";
				break;
			}
		}
		
		return response;
	}*/
	
	//Contacts repository layer to delete customer
	public String deleteCustomer(long phoneNumber)throws NoSuchCustomerException
	{
		return customerRepository.deleteCustomer(phoneNumber);
	}
}
