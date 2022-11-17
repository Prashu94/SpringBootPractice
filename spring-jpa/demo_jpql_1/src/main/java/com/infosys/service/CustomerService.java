package com.infosys.service;

import java.util.List;

import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface CustomerService {
    public List<CustomerDTO> getCustomerDetails() throws InfyBankException;
    public List<Object[]> getCustomerNameAndDob() throws InfyBankException;
    public List<String> getCustomerName() throws InfyBankException;
}
