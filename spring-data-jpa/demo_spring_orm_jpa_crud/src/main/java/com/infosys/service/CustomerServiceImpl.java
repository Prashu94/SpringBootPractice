package com.infosys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;
import com.infosys.repository.CustomerDAO;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Override
	public void insert(CustomerDTO customer) {
		customerDAO.insert(CustomerDTO.prepareCustomerEntity(customer));
	}

	@Override
	public int remove(Long phoneNo) {
		return customerDAO.remove(phoneNo);
	}

	@Override
	public List<CustomerDTO> getAll() {
		List<CustomerDTO> custList = new ArrayList<CustomerDTO>();
		List<Customer> custEntityList = customerDAO.getAll();
		for(Customer customerEntity: custEntityList) {
			CustomerDTO custDTO = Customer.prepareDTO(customerEntity);
			custList.add(custDTO);
		}
		return custList;
	}

	@Override
	public void update(Long phoneNo, String address) {
		customerDAO.update(phoneNo, address);
	}

}
