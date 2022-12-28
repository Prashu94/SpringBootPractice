package com.infosys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;
import com.infosys.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void insertCustomer(CustomerDTO customer) {
		customerRepository.saveAndFlush(CustomerDTO.prepareCustomerEntity(customer));
	}

	@Override
	public void removeCustomer(Long phoneNo) {
		customerRepository.deleteById(phoneNo);
	}

	@Override
	public CustomerDTO getCustomer(Long phoneNo) {
		Optional<Customer> optionalCustomer = customerRepository.findById(phoneNo);
		Customer customerEntity = optionalCustomer.get(); // Converting Optional<Customer> to Customer
		CustomerDTO customerDTO = Customer.prepareCustomerDTO(customerEntity);
		
		return customerDTO;
	}

	@Override
	public String updateCustomer(Long phoneNo, Integer newPlanId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(phoneNo);
		Customer customerEntity = optionalCustomer.get();
		customerEntity.setPlanId(newPlanId);
		customerRepository.save(customerEntity);
		return "The plan for the customer with phone Number :" + phoneNo + " has been updated successfully.";
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

}
