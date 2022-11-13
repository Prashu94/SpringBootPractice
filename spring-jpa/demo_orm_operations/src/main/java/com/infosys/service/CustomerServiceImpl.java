package com.infosys.service;

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
    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
        CustomerDTO customerDTO = customerRepository.getCustomerDTO(customerId);
        if(customerDTO == null){
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        return customerDTO;
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) throws InfyBankException {
        if(customerRepository.getCustomerDTO(customerDTO.getCustomerId())!=null){
            throw new InfyBankException("Service.CUSTOMER_ALREADY_EXISTS");
        }
        customerRepository.addCustomer(customerDTO);
    }

    @Override
    public Integer updateCustomer(Integer customerId, String emailId) throws InfyBankException {
        CustomerDTO customerDTO = customerRepository.getCustomerDTO(customerId);
        if(customerDTO == null){
            throw new InfyBankException("SERVICE.CUSTOMER_UNAVAILABLE");
        }
        return customerRepository.updateCustomer(customerId, emailId);
    }

    @Override
    public void deleteCustomer(Integer customerId) throws InfyBankException {
        CustomerDTO customerDTO = customerRepository.getCustomerDTO(customerId);
        if(customerDTO == null){
            throw new InfyBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerRepository.deleteCustomer(customerId);
        
    }
    
}
