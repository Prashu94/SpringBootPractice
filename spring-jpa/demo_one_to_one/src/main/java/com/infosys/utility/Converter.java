package com.infosys.utility;

import com.infosys.dto.AddressDTO;
import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Address;
import com.infosys.entity.Customer;

public class Converter {
	
	public static CustomerDTO convertCustomerEntityToDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setEmailId(customer.getEmailId());
		if(customer.getAddress()!=null) {
			customerDTO.setAddress(convertAddressEntityToDTO(customer.getAddress()));
		}
		return customerDTO;
	}
	
	public static AddressDTO convertAddressEntityToDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		
		addressDTO.setAddressId(address.getAddressId());
		addressDTO.setCity(address.getCity());
		addressDTO.setStreet(address.getCity());
		
		return addressDTO;
	}
	
	public static Customer convertCustomerDTOToEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		if(customerDTO.getAddress()!=null) {
			customer.setAddress(Converter.convertAddressDTOToEntity(customerDTO.getAddress()));
		}
		
		return customer;
	}
	
	public static Address convertAddressDTOToEntity(AddressDTO addressDTO) {
		Address address = new Address();
		address.setAddressId(addressDTO.getAddressId());
		address.setCity(addressDTO.getCity());
		address.setStreet(addressDTO.getStreet());
		return address;
	}
	
	
}
