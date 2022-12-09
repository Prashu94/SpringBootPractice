package com.infosys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.TransactionDTO;
import com.infosys.entity.Transaction;
import com.infosys.exception.InfyTransactionException;
import com.infosys.repository.TransactionRepository;
import com.infosys.validator.Validator;

@Service(value = "transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Integer makeTransaction(TransactionDTO transactionDTO) throws InfyTransactionException {
		Transaction transaction = new Transaction();
		Validator.validate(transactionDTO);
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setCustomerId(transactionDTO.getCustomerId());
		transaction.setTransactionDateTime(transactionDTO.getTransactionDateTime());
		transactionRepository.save(transaction);
		return transaction.getTransactionId();
	}

	@Override
	public TransactionDTO getTransactionDetails(Integer transactionId) throws InfyTransactionException {
		Optional<Transaction> optional = transactionRepository.findById(transactionId);
		Transaction transaction = optional.orElseThrow(() -> new InfyTransactionException("Service.INVALID_TRANSACTION_ID"));
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setAmount(transaction.getAmount());
		transactionDTO.setCustomerId(transaction.getCustomerId());
		transactionDTO.setTransactionId(transaction.getTransactionId());
		transactionDTO.setTransactionDateTime(transaction.getTransactionDateTime());
		return transactionDTO;
	}

}
