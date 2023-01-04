package com.infytel.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.infytel.dto.CustomerDTO;
import com.infytel.dto.PlanDTO;
import com.infytel.exceptions.NoSuchCustomerException;
import com.infytel.repository.CustomerRepository;
@Service
public class CustomerService
{
	@Autowired
	private CustomerRepository customerRepository;
	//Contacts repository layer to add customer, but before that PlanController is contacted for plan details
	public String createCustomer(CustomerDTO customerDTO)
	{
		PlanDTO planDTOReceived = new RestTemplate().getForObject("http://localhost:8080/infytel-8a/plans/"+customerDTO.getCurrentPlan().getPlanId(), PlanDTO.class);
		customerDTO.setCurrentPlan(planDTOReceived);
		return customerRepository.createCustomer(customerDTO);
	}
	//makes a call to repository method for returning a list of customers
	public List<CustomerDTO> fetchCustomer()
	{
		List<CustomerDTO> customers = customerRepository.fetchCustomer();
		//code that iterates through customers and set the password to *
		return customers.stream().map(c->{c.setPassword("*");return c;}).collect(Collectors.toList());
	}
	//Contacts repository layer to delete customer
	public String deleteCustomer(long phoneNumber)throws NoSuchCustomerException
	{
		return customerRepository.deleteCustomer(phoneNumber);
	}
	//Contacts repository layer to update customer
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO)
	{
		return customerRepository.updateCustomer(phoneNumber, customerDTO);
	}
	
}
