package com.infosys.utility;

import java.util.Set;

import com.infosys.dto.CustomerDTO;
import com.infosys.dto.ServicesDTO;
import com.infosys.entity.Customer;
import com.infosys.entity.Services;

public class Converter {
	
	public static Customer convertCustomerDTOToEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		return customer;
	}
	
	public static Services convertServicesDTOToEntity(ServicesDTO servicesDTO) {
		Services services = new Services();
		services.setServiceId(servicesDTO.getServiceId());
		services.setServiceCost(servicesDTO.getServiceCost());
		services.setServiceName(servicesDTO.getServiceName());
		return services;
	}
}
