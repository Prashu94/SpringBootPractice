package com.infosys;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.infosys.dto.TransactionDTO;
import com.infosys.service.TransactionService;

@SpringBootApplication
public class DemoSpringPaginationApplication implements CommandLineRunner {
	private static final Log LOGGER = LogFactory.getLog(DemoSpringPaginationApplication.class);
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringPaginationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getAllTransactions();
		getAllTransactionsByTransactionDate();
		getAllTransactionsV1();
		getAllTransactionsAfterTransactionDate();
	}
	
	public void getAllTransactions() {
		try {
			List<TransactionDTO> transactionList = transactionService.getAllTransaction(0, 5);
			transactionList.forEach(LOGGER::info);
		}catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occurred. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getAllTransactionsByTransactionDate() {
		try {
			LocalDate transactionDate = LocalDate.of(1996, 1, 29);
			List<TransactionDTO> transactionList = transactionService.getAllTransactionDateAfter(transactionDate, 0, 5);
			transactionList.forEach(LOGGER::info);
		}catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occurred. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getAllTransactionsV1() {
		try {
			Sort sort = Sort.by("transactionDate");
			List<TransactionDTO> transactionDTOs = transactionService.getAllTransaction(sort);
			transactionDTOs.forEach(LOGGER::info);
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getAllTransactionsAfterTransactionDate() {
		try {
			LocalDate transactionDate = LocalDate.of(1996, 1, 29);
			Sort sort = Sort.by("transactionDate");
			Pageable pageable = PageRequest.of(0, 2,sort);
			
			List<TransactionDTO> transactionDTOs = transactionService.getAllTransactionDateAfter(transactionDate, pageable);
			transactionDTOs.forEach(LOGGER::info);
			
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}

}
