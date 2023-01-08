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
	
	// Equivalent/similar to constructor, Here we populate the DTOs in a hard-coded manner
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
		customerDTO.setEmail("Jack@infy.com");
		customerDTO.setGender('m');
		customerDTO.setName("Jack");
		customerDTO.setPassword("ABC@123");
		customerDTO.setPhoneNo(9951212222l);
		
		List<FriendFamilyDTO> friendAndFamily = new ArrayList<FriendFamilyDTO>();
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(), 800000145));
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(), 700000145));
		
		customerDTO.setFriendAndFamily(friendAndFamily);
		
		customers = new ArrayList<CustomerDTO>();
		customers.add(customerDTO);
		
	}
	
	// adds the received customer object to the customer list
	public void createCustomer(CustomerDTO customerDTO) {
		customers.add(customerDTO);
	}
	
	// returns a list of customers
	public List<CustomerDTO> fetchCustomer(){
		return customers;
	}
	
	// deletes the passed customer from the list
	/*public void deleteCustomer(CustomerDTO customer) {
		customers.remove(customer);
	}*/
	// Commenting for New delete method
	/*public String deleteCustomer(long phoneNumber) throws NoSuchCustomerException
	{   boolean notfound=true;
		String response = "Customer of:"+phoneNumber+"\t does not exist";
		for(CustomerDTO customer : customers)
		{ 
			if(customer.getPhoneNo() == phoneNumber)
			{
				customers.remove(customer);
				response = customer.getName()+" with  phoneNumber "+customer.getPhoneNo()+" deleted successfully";
				notfound=false;
				break;
			
		}}
			if(notfound)
				throw new NoSuchCustomerException("Customer does not exist :"+phoneNumber);
		return response;
	}*/
	// deletes customer - exception handling incorporated
	public boolean deleteCustomer(long phoneNumber) {
		boolean responseDelete = false;
		for(CustomerDTO customer: customers) {
			if(customer.getPhoneNo() == phoneNumber) {
				customers.remove(customer);
				responseDelete = true;
				break;
			}
		}
		return responseDelete;
	}
	
	// finds the customer based on phoneNumber and updates the details of the same
	public boolean updateCustomer(long phoneNumber, CustomerDTO customerDTO) {
		boolean responseUpdate = false;
		
		for(CustomerDTO customer: customers) {
			if(customer.getPhoneNo() == phoneNumber) {
				if(customer.getAddress()!=null) {
					customer.setAddress(customerDTO.getAddress());
				}
				if(customer.getEmail()!=null) {
					customer.setEmail(customerDTO.getEmail());
				}
				responseUpdate = true;
				break;
			}
		}
		
		return responseUpdate;
	}
	
}
