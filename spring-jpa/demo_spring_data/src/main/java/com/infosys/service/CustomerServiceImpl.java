package com.infosys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.CustomerRepository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void addCustomer(CustomerDTO customer) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customer.getCustomerId());
		if(optional.isPresent()) {
			throw new InfyBankException("Service.CUSTOMER_FOUND");
		}
		Customer customerEntity = new Customer();
		customerEntity.setCity(customer.getCity());
		customerEntity.setCustomerId(customer.getCustomerId());
		customerEntity.setDateOfBirth(customer.getDateOfBirth());
		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setName(customer.getName());
		customerRepository.save(customerEntity);
	}

	@Override
	public List<CustomerDTO> findAll() throws InfyBankException {
		Iterable<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		if (customerDTOs.isEmpty())
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		return customerDTOs;
	}

	@Override
	public CustomerDTO findById(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCity(customer.getCity());
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setEmailId(customer.getEmailId());
		customerDTO.setName(customer.getName());
		return customerDTO;
	}

	@Override
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customer.setEmailId(emailId);
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customerRepository.delete(customer);
	}

}
