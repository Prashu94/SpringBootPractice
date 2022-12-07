package com.infosys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;
import com.infosys.repository.CustomerRepository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Integer addCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		
		customerRepository.save(customer);
		
		return customer.getCustomerId();
	}

}
