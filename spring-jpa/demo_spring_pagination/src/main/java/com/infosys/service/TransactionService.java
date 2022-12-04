package com.infosys.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.infosys.dto.TransactionDTO;
import com.infosys.exception.InfyBankException;

public interface TransactionService {
	public List<TransactionDTO> getAllTransaction(Integer pageNo, Integer pageSize) throws InfyBankException;
	public List<TransactionDTO> getAllTransactionDateAfter(LocalDate transactionDate, Integer pageNo, Integer pageSize) throws InfyBankException;
	public List<TransactionDTO> getAllTransaction(Sort sort) throws InfyBankException;
	public List<TransactionDTO> getAllTransactionDateAfter(LocalDate transactionDate, Pageable pageable) throws InfyBankException;
}
