package com.infosys.service;

import java.util.List;

import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface CustomerService {
    public List<CustomerDTO> getCustomerDetails() throws InfyBankException;
    public List<Object[]> getCustomerNameAndDob() throws InfyBankException;
    public List<String> getCustomerName() throws InfyBankException;
    public List<CustomerDTO> getCustomerDetails(Integer customerId) throws InfyBankException;
    public List<CustomerDTO> getCustomerDetailsV1(Integer customerId) throws InfyBankException;
    public List<CustomerDTO> getCustomerDetailsV2() throws InfyBankException;
    public Double getAverageBalance();
    public Long getTotalBalance();
    public Long getNumberOfAccounts();
    public Integer getMinimumBalance();
    public Integer getMaximumBalance();
    public List<Object[]> getCustomerCountForCities() throws InfyBankException;
    public Integer updateCityOfCustomer(Integer customerId, String city) throws InfyBankException;
    public Integer deleteCustomer() throws InfyBankException;
}
