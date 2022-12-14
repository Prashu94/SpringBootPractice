package com.infosys.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.TransactionDTO;
import com.infosys.entity.Transaction;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.TransactionRepository;

@Service(value = "transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<TransactionDTO> getAllTransaction(Integer pageNo, Integer pageSize) throws InfyBankException {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Transaction> page = transactionRepository.findAll(pageable);
		
		if(page.isEmpty()) {
			throw new InfyBankException("Service.NO_TRANSACTION_IN_THIS_PAGE");
		}
		List<Transaction> transactionList = page.getContent();
		
		List<TransactionDTO> transactionDTOs;
		
		transactionDTOs = transactionList.stream()
				.map(p -> new TransactionDTO(p.getTransactionId(), p.getTransactionDate(), p.getTransactionAmount()))
				.collect(Collectors.toList());
		
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> getAllTransactionDateAfter(LocalDate transactionDate, Integer pageNo, Integer pageSize)
			throws InfyBankException {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<Transaction> transactions = transactionRepository.findByTransactionDateAfter(transactionDate, pageable);
		if(transactions.isEmpty()) {
			throw new InfyBankException("Service.NO_TRANSACTION_IN_THIS_PAGE");
		}
		
		List<TransactionDTO> transasctionDTOs;
		
		transasctionDTOs = transactions.stream()
				.map(p -> new TransactionDTO(p.getTransactionId(), p.getTransactionDate(), p.getTransactionAmount()))
				.collect(Collectors.toList());
		return transasctionDTOs;
	}

	@Override
	public List<TransactionDTO> getAllTransaction(Sort sort) throws InfyBankException {
		Iterable<Transaction> transactions = transactionRepository.findAll(sort);
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		transactions.forEach(transaction -> {
			TransactionDTO transactionDTO = new TransactionDTO(transaction.getTransactionId(), transaction.getTransactionDate(), transaction.getTransactionAmount());
			transactionDTOs.add(transactionDTO);
		});
		
		if(transactionDTOs.isEmpty()) {
			throw new InfyBankException("Service.NO_TRANSACTION_IN_THIS_PAGE");
		}
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> getAllTransactionDateAfter(LocalDate transactionDate, Pageable pageable)
			throws InfyBankException {
		List<Transaction> transactions = transactionRepository.findByTransactionDateAfter(transactionDate, pageable);
		if(transactions.isEmpty()) {
			throw new InfyBankException("Service.NO_TRANSACTION_IN_THIS_PAGE");
		}
		return transactions.stream()
				.map(p -> new TransactionDTO(p.getTransactionId(), p.getTransactionDate(), p.getTransactionAmount()))
				.collect(Collectors.toList());
	}

}
