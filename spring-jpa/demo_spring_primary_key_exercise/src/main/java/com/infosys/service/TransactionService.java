package com.infosys.service;

import com.infosys.dto.TransactionDTO;
import com.infosys.exception.InfyTransactionException;

public interface TransactionService {
	public Integer makeTransaction(TransactionDTO transactionDTO) throws InfyTransactionException;
	public TransactionDTO getTransactionDetails(Integer transactionId) throws InfyTransactionException;
}
