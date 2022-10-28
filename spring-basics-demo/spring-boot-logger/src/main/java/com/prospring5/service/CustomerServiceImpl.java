package com.prospring5.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospring5.dto.CustomerDTO;
import com.prospring5.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public void logExceptionFromRepository(Exception exception) throws Exception{
		logger.info("In Log Exception");
		logger.error(exception.getMessage(), exception);
	}
	
	@Override
	public String createCustomer(CustomerDTO customer) {
		return customerRepository.createCustomer(customer);
	}

	@Override
	public String fetchCustomer() {
		return customerRepository.fetchCustomer();
	}

	@Override
	public void deleteCustomer(long phoneNumber) throws Exception {
		try {
			customerRepository.deleteCustomers(phoneNumber);
		}catch(Exception e) {
			logger.info("In Log Exception");
			logger.error(e.getMessage(), e);
		}
	}

}
