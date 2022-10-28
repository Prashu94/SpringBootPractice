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
		customers = new ArrayList<CustomerDTO>();
		customers.add(customerDTO);
	}
	
	public String createCustomer(CustomerDTO dto) {
		customers = new ArrayList<CustomerDTO>();
		customers.add(dto);
		return "Customer added successfully " + customers.indexOf(dto);
	}
	
	public String fetchCustomer() {
		return " The customer fetched " + customers;
	}
	
	public void deleteCustomers(long phoneNumber) throws Exception {
		for(CustomerDTO customer: customers) {
			if(customer.getPhoneNo() == phoneNumber) {
				customers.remove(customer);
				System.out.println(customer.getName()+"of phoneNumber"+customer.getPhoneNo()+"\t got deleted successfully");
				break;
			}else {
				throw new Exception("Customer does not exist");
			}
		}
	}
}
