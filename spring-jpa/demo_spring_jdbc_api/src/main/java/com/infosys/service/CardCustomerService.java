package com.infosys.service;

import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface CardCustomerService {
    public CustomerDTO getCustomerDetails(Integer customerId) throws InfyBankException;
}
