package com.infytel.repository;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;


import org.springframework.stereotype.Repository;
import com.infytel.dto.CustomerDTO;
import com.infytel.dto.FriendFamilyDTO;
import com.infytel.dto.PlanDTO;


@Repository
public class CustomerRepository 
{
	
	List<CustomerDTO> customers = null;
	//Populates customer in hard-coded way
	@PostConstruct
	public void initializer()
	{
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
		customers = new ArrayList<>();
		customers.add(customerDTO);
	}
	//creates customer
	public String createCustomer(CustomerDTO customerDTO)
	{
		customers.add(customerDTO);
		return "Customer details got added successfully";
		//since this code deals with a hard-coded list and not the actual repository, return string gets hard-coded here.
		//The service layer will deal with the exception or the response, otherwise.
		//In such cases, its preferred to make the exception and the success message available in the ValidationMessages.properties
		//And, these messages can be taken as how the methods of CustomerService do
	}
	//fetches customer
	public List<CustomerDTO> fetchCustomer()
	{
		return customers;
	}
	//deletes customer - exception handling incorporated
	public boolean deleteCustomer(long phoneNumber) 
	{   
		boolean responseDelete=false;
		for(CustomerDTO customer : customers)
		{ 
			if(customer.getPhoneNo() == phoneNumber)
			{
				customers.remove(customer);
				responseDelete=true;
				break;
			}
		}
		return responseDelete;
	}
	//finds the customer based on phoneNumber and updates the details of the same
		public boolean updateCustomer(long phoneNumber, CustomerDTO customerDTO)
		{
			boolean responseUpdate=false;
			
			for(CustomerDTO customer : customers)
			{
				if(customer.getPhoneNo() == phoneNumber)
				{
					if(customerDTO.getAddress()!=null)
					{
						customer.setAddress(customerDTO.getAddress());
					}
					if(customerDTO.getEmail()!=null)
					{
						customer.setEmail(customerDTO.getEmail());
					}
					responseUpdate = true;
					break;
				}
			}
			return responseUpdate;
		}

	
	
 
}
