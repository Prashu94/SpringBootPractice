package com.infosys.service;

import java.time.LocalDate;
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

	@Override
	public CustomerDTO findByEmailId(String emailId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findByEmailId(emailId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCity(customer.getCity());
		customerDTO.setName(customer.getName());
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setEmailId(customer.getEmailId());
		
		return customerDTO;
	}

	@Override
	public CustomerDTO findByEmailIdAndName(String emailId, String name) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findByEmailIdAndName(emailId, name);
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
	public List<CustomerDTO> findByEmailIdOrName(String emailId, String name) throws InfyBankException {
		List<Customer> customers = customerRepository.findByEmailIdOrName(emailId, name);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByDateOfBirthBetween(LocalDate fromDate, LocalDate toDate) throws InfyBankException {
		List<Customer> customers = customerRepository.findByDateOfBirthBetween(fromDate, toDate);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByDateOfBirthLessThan(LocalDate dateOfBirth) throws InfyBankException {
		List<Customer> customers = customerRepository.findByDateOfBirthLessThan(dateOfBirth);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByDateOfBirthGreaterThan(LocalDate dateOfBirth) throws InfyBankException {
		List<Customer> customers = customerRepository.findByDateOfBirthGreaterThan(dateOfBirth);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByDateOfBirthAfter(LocalDate dateOfBirth) throws InfyBankException {
		List<Customer> customers = customerRepository.findByDateOfBirthAfter(dateOfBirth);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByDateOfBirthBefore(LocalDate dateOfBirth) throws InfyBankException {
		List<Customer> customers = customerRepository.findByDateOfBirthBefore(dateOfBirth);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByEmailIdNull() throws InfyBankException {
		List<Customer> customers = customerRepository.findByEmailIdNull();
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByNameLike(String pattern) throws InfyBankException {
		List<Customer> customers = customerRepository.findByNameLike(pattern);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByNameOrderByDateOfBirth(String name) throws InfyBankException {
		List<Customer> customers = customerRepository.findByNameOrderByDateOfBirth(name);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public List<CustomerDTO> findByNameOrderByDateOfBirthDesc(String name) throws InfyBankException {
		List<Customer> customers = customerRepository.findByNameOrderByDateOfBirthDesc(name);
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		
		if(customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(customer.getCity());
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public String findByNameByEmailId(String emailId) throws InfyBankException {
		String name = customerRepository.findNameByEmailId(emailId);
		if(name==null) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}
		return name;
	}

	@Override
	public void updateCustomerEmailId(String newEmailId, Integer customerId) throws InfyBankException {
		
		Optional<Customer> optional = customerRepository.findById(customerId);
		
		optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		
		customerRepository.updateCustomerEmailId(newEmailId, customerId);
		
	}

	@Override
	public void deleteCustomerByEmailId(String emailId) throws InfyBankException {
		Integer count = customerRepository.deleteCustomerByEmailId(emailId);
		
		if(count == 0) {
			throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
		}
		
	}

	@Override
	public String findNameByEmailId(String emailId) {
		return customerRepository.findNameByEmailIdV1(emailId);
	}

}
