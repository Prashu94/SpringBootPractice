package com.infosys;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infosys.dto.TransactionDTO;
import com.infosys.service.TransactionService;

@SpringBootApplication
public class DemoSpringPrimaryKeyExerciseApplication implements CommandLineRunner {
	public static final Log LOGGER = LogFactory.getLog(DemoSpringPrimaryKeyExerciseApplication.class);
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringPrimaryKeyExerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		makeTransaction();
		getTransactionDetails();
	}
	
	
	public void makeTransaction() {
		try {
			TransactionDTO transactionDTO = new TransactionDTO();
			transactionDTO.setCustomerId("C5001");
			transactionDTO.setTransactionDateTime(LocalDate.now());
			transactionDTO.setAmount(150.40d);

			Integer transactionId = transactionService.makeTransaction(transactionDTO);

			LOGGER.info(environment.getProperty("UserInterface.TRANSACTION_SUCCESSFUL") + transactionId);

		} catch (Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured. Please check log file."));

		}
	}

	public void getTransactionDetails() {
		try {

			TransactionDTO transactionDTO = transactionService.getTransactionDetails(1004);

			LOGGER.info(transactionDTO);

		} catch (Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured. Please check log file."));

		}

	}
}
