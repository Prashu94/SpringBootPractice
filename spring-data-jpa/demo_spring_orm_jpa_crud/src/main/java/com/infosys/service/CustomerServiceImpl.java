package com.infosys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.CustomerDTO;
import com.infosys.repository.CustomerDAO;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO customerDAO;
	@Override
	public void insert(CustomerDTO customer) {
		
	}

	@Override
	public int remove(Long phoneNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomerDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long phoneNo, String address) {
		// TODO Auto-generated method stub
		
	}

}
