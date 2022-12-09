package com.infosys.validator;

import com.infosys.dto.TransactionDTO;
import com.infosys.exception.InfyTransactionException;

public class Validator {
	
	public static void validate(TransactionDTO transactionDTO) throws InfyTransactionException{
		if(!validateAmount(transactionDTO.getAmount())) {
			throw new InfyTransactionException("Validator.INVALID_AMOUNT");
		}
		if(!validateCustomerId(transactionDTO.getCustomerId())) {
			throw new InfyTransactionException("Validator.INVALID_CUSTOMER_ID");
		}
	}
	
	public static Boolean validateAmount(Double amount) {
		if(amount > 0d) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static Boolean validateCustomerId(String customerId) {
		if(customerId.startsWith("C") && customerId.length()  == 5) {
			return true;
		}else {
			return false;
		}
	}
}
