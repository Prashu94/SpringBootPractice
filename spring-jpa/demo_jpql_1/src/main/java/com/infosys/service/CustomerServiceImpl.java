package com.infosys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.CustomerRepository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public List<CustomerDTO> getCustomerDetails() throws InfyBankException {
        return customerRepository.getCustomerDetails();
    }

    @Override
    public List<Object[]> getCustomerNameAndDob() throws InfyBankException {
        return customerRepository.getCustomerNameAndDob();
    }

    @Override
    public List<String> getCustomerName() throws InfyBankException {
        return customerRepository.getCustomerName();
    }
    
}
