package com.infosys.service;

import java.util.List;

import com.infosys.dto.CardDTO;
import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface CardCustomerService {
    public CustomerDTO getCustomerDetails(Integer customerId) throws InfyBankException;
    public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException;
    public void issueCardToExistingCustomer(Integer customerId, CardDTO cardDTO) throws InfyBankException;
    public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) throws InfyBankException;
    public void deleteCustomer(Integer customerId) throws InfyBankException;
}
