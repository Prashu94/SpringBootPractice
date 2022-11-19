package com.infosys.repository;

import java.util.List;

import com.infosys.dto.CustomerDTO;

public interface CustomerRepository {
    public List<CustomerDTO> getCustomerDetails();
    public List<Object[]> getCustomerNameAndDob();
    public List<String> getCustomerName();
    public List<CustomerDTO> getCustomerDetails(Integer customerId);
    public List<CustomerDTO> getCustomerDetailsV1(Integer customerId);
    public List<CustomerDTO> getCustomerDetailsWhereClauses();
    public Double getAverageBalance();
    public Long getTotalBalance();
    public Long getNumberOfAccounts();
    public Integer getMinimumBalance();
    public Integer getMaximumBalance();
    public List<Object[]> getCustomerCountForCities();
    public Integer updateCityOfCustomer(Integer customerId, String city);
    public Integer deleteCustomer();
}
