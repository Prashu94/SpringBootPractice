package com.infosys.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.infosys.dto.CustomerDTO;
import com.infosys.dto.FriendFamilyDTO;
import com.infosys.dto.PlanDTO;
import com.infosys.exceptions.NoSuchCustomerException;

@Repository
public class CustomerRepository {
	
	List<CustomerDTO> customers = null;
	
	@PostConstruct
	public void initializer() {
		CustomerDTO customerDTO = new CustomerDTO();
		PlanDTO planDTO = new PlanDTO();
		planDTO.setPlanId(1);
		planDTO.setPlanName("Simple");
		planDTO.setLocalRate(3);
		planDTO.setNationalRate(5);
		customerDTO.setAddress("Chennai");
		customerDTO.setAge(18);
		customerDTO.setCurrentPlan(planDTO);
		customerDTO.setGender('m');
		customerDTO.setName("Jack");
		customerDTO.setEmail("Jack@infy.com");
		customerDTO.setPassword("ABC@123");
		customerDTO.setPhoneNo(9951212222l);
		List<FriendFamilyDTO> friendAndFamily = new ArrayList<>();
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(), 800000145));
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(), 700000145));
		customerDTO.setFriendAndFamily(friendAndFamily);
		customers = new ArrayList<>();
		customers.add(customerDTO);
	}
	
	// method to create a customer
	public String createCustomer(CustomerDTO customerDTO) {
		customers.add(customerDTO);
		return "Customer with "+ customerDTO.getPhoneNo() + " added successfully";
	}
	
	// method to fetch the customer
	public List<CustomerDTO> fetchCustomer(){
		return customers;
	}
	
	// method to delete customer - exception handling incorporated
	public String deleteCustomer(long phoneNumber) throws NoSuchCustomerException{
		boolean notFound = true;
		String response = "Customer of: "+ phoneNumber + "\t does not exist";
		for(CustomerDTO customer: customers) {
			if(customer.getPhoneNo() == phoneNumber) {
				customers.remove(customer);
				response = customer.getName() + " with phoneNumber "+ customer.getPhoneNo() + " deleted successfully";
				notFound = false;
				break;
			}
		}
		if(notFound) {
			throw new NoSuchCustomerException("Customer does not exist: "+ phoneNumber);
		}
		return response;
	}
}
