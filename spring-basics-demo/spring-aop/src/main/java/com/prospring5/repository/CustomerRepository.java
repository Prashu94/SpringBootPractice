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
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setAddress("Chennai");
		customerDTO.setName("Jack");
		customerDTO.setEmail("Jack@infy.com");
		customerDTO.setPhoneNo(9951212222l);
		customers = new ArrayList<>();
		customers.add(customerDTO);

	}
	
	// adds the received customer object to customers list
	public void createCustomer(CustomerDTO customerDTO) {
		customers.add(customerDTO);
	}
	
	// returns a list of customers
	public List<CustomerDTO> fetchCustomers(){
		return customers;
	}
	
	// deletes the customer
	public String deleteCustomer(long phoneNumber) {
		String response = "Customer of: "+ phoneNumber + "\t does not exist";
		for(CustomerDTO customer: customers) {
			if(customer.getPhoneNo() == phoneNumber) {
				customers.remove(customer);
				response = customer.getName() + "of phoneNumber" + customer.getPhoneNo()
				+ "\t got deleted successfully";
				break;
			}
		}
		return response;
	}
	
	// updates customer
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO) {
		String response = "Customer of: "+ phoneNumber + "\t does not exist";
		for(CustomerDTO customer: customers) {
			if(customer.getPhoneNo() == phoneNumber) {
				if(customer.getName() != null) {
					customer.setName(customerDTO.getName());
				}
				if(customer.getAddress() != null) {
					customer.setAddress(customer.getAddress());
				}
				
				customers.set(customers.indexOf(customer), customer);
				
				response = "Customer of phoneNumber"+ customer.getPhoneNo() + "\t got updated successfully";
				break;
			}
		}
		return response;
	}
}
