package com.infosys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.LoanDTO;
import com.infosys.entity.Customer;
import com.infosys.entity.Loan;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.CustomerRepository;
import com.infosys.repository.LoanRepository;
import com.infosys.utility.Converter;

@Service(value = "customerLoanService")
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public LoanDTO getLoanDetails(Integer loanId) throws InfyBankException {
		// Get the optional records for the specific loan id
		Optional<Loan> optional = loanRepository.findById(loanId);
		// Get the Loan entity
		Loan loan = optional.orElseThrow(() -> new InfyBankException("Service.LOAN_UNAVAILABLE"));
		// Get the Loan DTO object
		LoanDTO loanDTO = Converter.convertLoanEntityToLoanDTO(loan);
		return loanDTO;
	}

	@Override
	public Integer addLoanAndCustomer(LoanDTO loanDTO) throws InfyBankException {
		Loan loan = Converter.convertLoanDTOToEntity(loanDTO);
		loanRepository.save(loan);
		return loan.getLoanId();
	}

	@Override
	public Integer sanctionLoanToExistingCustomer(Integer customerId, LoanDTO loanDTO) throws InfyBankException {
		Loan loan = Converter.convertLoanDTOToEntity(loanDTO);
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
		loan.setCustomer(customer);
		
		loanRepository.save(loan);
		
		return loan.getLoanId();
	}

	@Override
	public void closeLoan(Integer loanId) throws InfyBankException {
		Optional<Loan> optional = loanRepository.findById(loanId);
		Loan loan = optional.orElseThrow(() -> new InfyBankException("Service.LOAN_UNAVAILABLE"));
		loan.setStatus("Closed");
		loanRepository.save(loan);
	}

	@Override
	public void deleteLoan(Integer loanId) throws InfyBankException {
		Optional<Loan> optional = loanRepository.findById(loanId);
		Loan loan = optional.orElseThrow(()->new InfyBankException("Service.LOAN_UNAVAILABLE"));
		// first dissaccosiate the customer from the loan entity to delete the loan.
		loan.setCustomer(null);
		loanRepository.delete(loan);
	}
	
	
}
