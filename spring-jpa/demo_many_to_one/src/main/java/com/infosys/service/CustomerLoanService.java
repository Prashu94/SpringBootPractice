package com.infosys.service;

import com.infosys.dto.LoanDTO;
import com.infosys.exception.InfyBankException;

public interface CustomerLoanService {
	public LoanDTO getLoanDetails(Integer loanId) throws InfyBankException;
	public Integer addLoanAndCustomer(LoanDTO loanDTO) throws InfyBankException;
	public Integer sanctionLoanToExistingCustomer(Integer customerId, LoanDTO loanDTO) throws InfyBankException;
	public void closeLoan(Integer loanId) throws InfyBankException;
	public void deleteLoan(Integer loanId) throws InfyBankException;
}
