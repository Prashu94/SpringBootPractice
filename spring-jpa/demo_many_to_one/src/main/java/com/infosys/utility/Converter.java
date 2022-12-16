package com.infosys.utility;

import com.infosys.dto.CustomerDTO;
import com.infosys.dto.LoanDTO;
import com.infosys.entity.Customer;
import com.infosys.entity.Loan;

public class Converter {
	
	public static LoanDTO convertLoanEntityToLoanDTO(Loan loan) {
		LoanDTO loanDTO = new LoanDTO();
		loanDTO.setAmount(loan.getAmount());
		loanDTO.setLoanId(loan.getLoanId());
		loanDTO.setLoanIssueDate(loan.getIssueDate());
		loanDTO.setStatus(loan.getStatus());
		// Get the customer entity
		Customer customer = loan.getCustomer();
		if(customer!=null) {
			// convert the entity to DTO
			CustomerDTO customerDTO = convertCustomerEntityToDTO(customer);
			// set the customerDTO object
			loanDTO.setCustomer(customerDTO);
		}

		return loanDTO;
	}
	
	public static CustomerDTO convertCustomerEntityToDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setEmailId(customer.getEmailId());
		customerDTO.setName(customer.getName());
		return customerDTO;
	}
	
	public static Loan convertLoanDTOToEntity(LoanDTO loanDTO) {
		Loan loan = new Loan();
		loan.setAmount(loanDTO.getAmount());
		loan.setIssueDate(loanDTO.getLoanIssueDate());
		loan.setStatus("open");
		if(loanDTO.getCustomer()!=null) {
			CustomerDTO customerDTO = loanDTO.getCustomer();
			Customer customer = Converter.convertCustomerDTOToEntity(customerDTO);
			
			// set the loan to customer
			loan.setCustomer(customer);
		}
		return loan;
	}
	
	public static Customer convertCustomerDTOToEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		
		return customer;
	}
}
