package com.infytel.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.infytel.dto.CustomerDTO;
import com.infytel.dto.FriendFamilyDTO;
import com.infytel.dto.PlanDTO;

@Repository
public class CustomerRepository {

	List<CustomerDTO> customers;
	
	
	//Similar to the constructor. Populates the DTOs in a hard-coded way
	@PostConstruct
	public void initializer()
	{
		customers = new ArrayList<>();
		
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
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(),800000145));
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(),700000145));
		
		customerDTO.setFriendAndFamily(friendAndFamily);
		
		
		customers.add(customerDTO);
	
		
		
	}
	
	//adds the received customer object to customers list
	public void createCustomer(CustomerDTO customerDTO)
	{
		customers.add(customerDTO);
	}
	
	//returns a list of customers
	public List<CustomerDTO> fetchCustomer()
	{
		
		return customers;
	}
}
