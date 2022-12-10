package com.infosys.service;

import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface AddressService {
	public Integer addCustomerWithExistingAddress(Integer addressId, CustomerDTO customerDTO) throws InfyBankException;
}
