package com.infosys.repository;

import java.util.List;

import com.infosys.dto.CustomerDTO;

public interface CustomerRepository {
    public List<CustomerDTO> getCustomerDetails();
    public List<Object[]> getCustomerNameAndDob();
    public List<String> getCustomerName();
}
