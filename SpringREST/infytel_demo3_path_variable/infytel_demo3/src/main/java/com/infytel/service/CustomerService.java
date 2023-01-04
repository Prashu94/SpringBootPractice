package com.infytel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infytel.dto.CustomerDTO;
import com.infytel.repository.CustomerRepository;

@Service
public class CustomerService 
{
	/* CustomerService needs to contact CustomerRepository, 
	 * hence injecting the customerRepository dependency
	 */
	@Autowired
	private CustomerRepository customerRepository;
	
	//makes a call to repository method for adding the customer
	public String createCustomer(CustomerDTO customerDTO)
	{
		customerRepository.createCustomer(customerDTO);
		
		return "Customer with "+customerDTO.getPhoneNo()+" added successfully";
	}
	
	//makes a call to repository method for returning a list of customers
	public List<CustomerDTO> fetchCustomer()
	{
		List<CustomerDTO> customers = customerRepository.fetchCustomer();
		//code that iterates through customers and set the password to *
		return customers.stream().map(c->{c.setPassword("*");return c;}).collect(Collectors.toList());
	}
	
	// makes a call to repository method for updating the customer details
	 
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO)
	{
		return customerRepository.updateCustomer(phoneNumber, customerDTO);
	}
	
	/* makes a call to repository method for fetching the customers list and
	 * then calls the repository's deleteCustomer() method with the customer to be deleted 
	 */
	public String deleteCustomer(long phoneNumber)
	{
		String response = "Customer of: "+phoneNumber+" does not exist";
		
		for (CustomerDTO customer : customerRepository.fetchCustomer()) {
			if (customer.getPhoneNo() == phoneNumber) {
				
				customerRepository.deleteCustomer(customer);
				
				response = customer.getName() + " of phoneNumber " + customer.getPhoneNo()
						+ " got deleted successfully";
				break;
			}
		}
		 
		return response;
	}
	
}
