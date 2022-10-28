package com.prospring5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospring5.dao.CustomerDAO;
import com.prospring5.dto.CustomerDTO;
import com.prospring5.entity.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

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
	public List<CustomerDTO> geAll() {
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
