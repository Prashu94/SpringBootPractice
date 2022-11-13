package com.infosys.service;

import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface CustomerService {
    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException;
    public void addCustomer(CustomerDTO customerDTO) throws InfyBankException;
    public Integer updateCustomer(Integer customerId, String emailId) throws InfyBankException;
    public void deleteCustomer(Integer customerId) throws InfyBankException;
}
