package com.infosys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.AddressDTO;
import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Address;
import com.infosys.entity.Customer;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.AddressRepository;
import com.infosys.repository.CustomerRepository;
import com.infosys.utility.Converter;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		
		Customer customer = optional.orElseThrow(() ->  new InfyBankException("Service.INVALID_CUSTOMERID"));
		
		return Converter.convertCustomerEntityToDTO(customer);
		
	}

	@Override
	public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException {
		Customer customer = Converter.convertCustomerDTOToEntity(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		
		Address address = savedCustomer.getAddress();
		address.setCustomer(savedCustomer);
		addressRepository.save(address);
		
		return customer.getCustomerId();
	}

	@Override
	public void updateAddress(Integer customerId, AddressDTO addressDTO) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.INVALID_CUSTOMERID"));
		
		// update the address
		Address address = customer.getAddress();
		address.setCity(addressDTO.getCity());
		address.setStreet(addressDTO.getStreet());
		
		// associate the new address to the existing entity
		customer.setAddress(address);
		
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.INVALID_CUSTOMERID"));
		customerRepository.delete(customer);
	}

	@Override
	public void deleteCustomerOnly(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.INVALID_CUSTOMERID"));
		
		// disassociate the child i.e address form the customer entity so that delete of parent is possible
		Address address = customer.getAddress();
		address.setCustomer(null);
		customer.setAddress(null);
		//customer.getAddress().getCustomer().setAddress(null);
		
		customerRepository.delete(customer);
	}

	

}
