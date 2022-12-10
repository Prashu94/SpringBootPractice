package com.infosys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Address;
import com.infosys.entity.Customer;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.AddressRepository;
import com.infosys.repository.CustomerRepository;
import com.infosys.utility.Converter;

@Service(value = "addressService")
@Transactional
public class AddressServiceImpl implements AddressService{
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	@Override
	public Integer addCustomerWithExistingAddress(Integer addressId, CustomerDTO customerDTO) throws InfyBankException {
		Optional<Address> optional = addressRepository.findById(addressId);
		Address address = optional.orElseThrow(() -> new InfyBankException("Service.INVALID_ADDRESSID"));
		Customer customer = Converter.convertCustomerDTOToEntity(customerDTO);
		customer.setAddress(address);
		customerRepository.save(customer);
		//address.setCustomer(customer);
		//addressRepository.save(address);
		
		return customer.getCustomerId();
	}
	
}
