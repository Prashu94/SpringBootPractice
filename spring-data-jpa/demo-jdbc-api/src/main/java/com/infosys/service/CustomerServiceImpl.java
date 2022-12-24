package com.infosys.service;

import java.util.List;

import com.infosys.dto.CustomerDTO;
import com.infosys.repository.CustomerDAO;
import com.infosys.repository.CustomerDAOImpl;

public class CustomerServiceImpl implements CustomerService {
	CustomerDAO customerDAO = new CustomerDAOImpl();

	@Override
	public void insert(CustomerDTO customer) {
		customerDAO.insert(CustomerDTO.prepareCustomerEntity(customer));
	}

	@Override
	public int remove(Long phoneNo) {
		return customerDAO.remove(phoneNo);
	}

	@Override
	public List<CustomerDTO> findAll() {
		return customerDAO.findAll();
	}

	@Override
	public CustomerDTO findByPhoneNumber(Long phoneNumber) {
		return customerDAO.findByPhoneNumber(phoneNumber);
	}

}
