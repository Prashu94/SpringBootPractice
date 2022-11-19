package com.infosys.service;

import java.util.List;

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
    public List<CustomerDTO> getCustomerDetails() throws InfyBankException {
        return customerRepository.getCustomerDetails();
    }

    @Override
    public List<Object[]> getCustomerNameAndDob() throws InfyBankException {
        return customerRepository.getCustomerNameAndDob();
    }

    @Override
    public List<String> getCustomerName() throws InfyBankException {
        return customerRepository.getCustomerName();
    }

	@Override
	public List<CustomerDTO> getCustomerDetails(Integer customerId) throws InfyBankException {
		return customerRepository.getCustomerDetails(customerId);
	}

	@Override
	public List<CustomerDTO> getCustomerDetailsV1(Integer customerId) throws InfyBankException {
		return customerRepository.getCustomerDetailsV1(customerId);
	}

	@Override
	public List<CustomerDTO> getCustomerDetailsV2() throws InfyBankException {
		return customerRepository.getCustomerDetailsWhereClauses();
	}

	@Override
	public Double getAverageBalance() {
		return customerRepository.getAverageBalance();
	}

	@Override
	public Long getTotalBalance() {
		return customerRepository.getTotalBalance();
	}

	@Override
	public Long getNumberOfAccounts() {
		return customerRepository.getNumberOfAccounts();
	}

	@Override
	public Integer getMinimumBalance() {
		return customerRepository.getMinimumBalance();
	}

	@Override
	public Integer getMaximumBalance() {
		return customerRepository.getMaximumBalance();
	}

	@Override
	public List<Object[]> getCustomerCountForCities() throws InfyBankException {
		return customerRepository.getCustomerCountForCities();
	}

	@Override
	public Integer updateCityOfCustomer(Integer customerId, String city) throws InfyBankException {
		return customerRepository.updateCityOfCustomer(customerId, city);
	}

	@Override
	public Integer deleteCustomer() throws InfyBankException {
		return customerRepository.deleteCustomer();
	}
    
}
