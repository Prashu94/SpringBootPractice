package com.infytel.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.infytel.dto.CustomerDTO;
import com.infytel.exceptions.NoSuchCustomerException;
import com.infytel.repository.CustomerRepository;
import com.infytel.util.InfyTelConstants;
@Service
@PropertySource("classpath:ValidationMessages.properties")
public class CustomerService
{
	@Autowired
	private Environment environment;
	@Autowired
	private CustomerRepository customerRepository;
	//Contacts repository layer to add customer
	public String createCustomer(CustomerDTO customerDTO)
	{
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
		boolean response = customerRepository.deleteCustomer(phoneNumber);
		if(!response)
			throw new NoSuchCustomerException(environment.getProperty(InfyTelConstants.CUSTOMER_NOT_FOUND.toString()));
		return environment.getProperty(InfyTelConstants.CUSTOMER_DELETE_SUCCESS.toString());
		
	}
	//Contacts repository layer to update customer
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO) throws NoSuchCustomerException
	{
		boolean response = customerRepository.updateCustomer(phoneNumber,customerDTO);
		if(!response)
			throw new NoSuchCustomerException(environment.getProperty(InfyTelConstants.CUSTOMER_NOT_FOUND.toString()));
		return environment.getProperty(InfyTelConstants.CUSTOMER_UPDATE_SUCCESS.toString());
	}
}
