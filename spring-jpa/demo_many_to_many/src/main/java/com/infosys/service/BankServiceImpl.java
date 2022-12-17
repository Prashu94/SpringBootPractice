package com.infosys.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.CustomerDTO;
import com.infosys.dto.ServicesDTO;
import com.infosys.entity.Customer;
import com.infosys.entity.Services;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.CustomerRepository;
import com.infosys.repository.ServiceRepository;
import com.infosys.utility.Converter;

@Service(value = "bankService")
@Transactional
public class BankServiceImpl implements BankService{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ServiceRepository servicesRepository;
	
	@Override
	public Integer addCustomerAndService(CustomerDTO customerDTO) throws InfyBankException {
		Integer customerId = null;
		Set<ServicesDTO> bankServicesDTO = customerDTO.getBankServices();
		
		Customer customer = Converter.convertCustomerDTOToEntity(customerDTO);
		Set<Services> bankServices = null;
		
		if(bankServicesDTO!=null && !bankServicesDTO.isEmpty()) {
			bankServices = new LinkedHashSet<Services>();
			for(ServicesDTO servicesDTO: bankServicesDTO) {
				Services services = Converter.convertServicesDTOToEntity(servicesDTO);
				bankServices.add(services);
			}
			customer.setBankServices(bankServices);
		}
		
		customerRepository.save(customer);
		customerId = customer.getCustomerId();
		return customerId;
	}

	@Override
	public void addExistingServiceToExistingCustomer(Integer customerId, List<Integer> serviceIds)
			throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
		
		for(Integer serviceId: serviceIds) {
			Optional<Services> optional1 = servicesRepository.findById(serviceId);
			Services service = optional1.orElseThrow(() -> new InfyBankException("Service.SERVICE_UNAVAILABLE"));
			if(!customer.getBankServices().contains(service)) {
				customer.getBankServices().add(service);
			}
		}
		
	}

	@Override
	public void deallocateServiceForExistingCustomer(Integer customerId, List<Integer> serviceIds)
			throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
		
		Set<Services> bankServices = customer.getBankServices();
		for(Integer serviceId: serviceIds) {
			Optional<Services> optional1 = servicesRepository.findById(serviceId);
			if(optional.isPresent()) {
				Services service = optional1.get();
				bankServices.remove(service);
			}
		}
	}

	@Override
	public void deleteCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_CANNOT_DELETE"));
		customer.setBankServices(null);
		customerRepository.delete(customer);
	}

}
