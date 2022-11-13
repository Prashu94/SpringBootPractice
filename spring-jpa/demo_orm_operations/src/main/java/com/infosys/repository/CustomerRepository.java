package com.infosys.repository;

import com.infosys.dto.CustomerDTO;

public interface CustomerRepository {
    public CustomerDTO getCustomerDTO(Integer customerId);
    public void addCustomer(CustomerDTO customerDTO);
    public Integer updateCustomer(Integer customerId, String emailId);
    public Integer deleteCustomer(Integer customerId);
}
