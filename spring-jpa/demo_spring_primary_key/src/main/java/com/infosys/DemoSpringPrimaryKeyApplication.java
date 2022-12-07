package com.infosys;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infosys.dto.CustomerDTO;
import com.infosys.service.CustomerService;

@SpringBootApplication
public class DemoSpringPrimaryKeyApplication implements CommandLineRunner {
	private static final Log LOGGER = LogFactory.getLog(DemoSpringPrimaryKeyApplication.class);
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringPrimaryKeyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		addCustomer();
	}
	
	public void addCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setEmailId("tim@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1994, 01, 12));
		customerDTO.setName("Tim");
		try {
			Integer id = customerService.addCustomer(customerDTO);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS") + id);
		}catch (Exception e) {
		
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),"Something went wrong. Please check log file for more details."));
		}
	}

}
