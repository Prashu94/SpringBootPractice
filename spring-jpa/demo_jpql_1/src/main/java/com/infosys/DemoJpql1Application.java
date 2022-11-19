package com.infosys;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infosys.dto.CustomerDTO;
import com.infosys.service.CustomerService;

@SpringBootApplication
public class DemoJpql1Application implements CommandLineRunner{
	private static Logger LOGGER = LogManager.getLogger(DemoJpql1Application.class);

	@Autowired
	CustomerService service;
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoJpql1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getCustomerDetails();
		getCustomerNameAndDob();
		getCustomerNames();
		getCustomerDetailsWhereV1();
		getCustomerDetailsWhereV2();
		getCustomterDetailsWhereClauses();
		getAverageBalance();
		getTotalBalance();
		getNumberOfAccounts();
		getMinimumBalance();
		getMaximumBalance();
		getCityWiseCustomerCount();
		updateCityOfEmployee();
		deleteCustomer();
	}

	public void getCustomerDetails(){
		try{
			List<CustomerDTO> customerDTOs = service.getCustomerDetails();
			for(CustomerDTO customerDTO: customerDTOs){
				LOGGER.info(customerDTO);
			}
			LOGGER.info("\n");
		}catch(Exception e){
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}

	public void getCustomerNameAndDob(){
		try{
			List<Object[]> objects = service.getCustomerNameAndDob();
			for(Object [] object: objects){
				LOGGER.info(object[0]+"\t\t"+object[1]);
			}
			LOGGER.info("\n");
		}catch(Exception e){
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info( message);
		}
	}

	public void getCustomerNames(){
		try{
			List<String> customerNames = service.getCustomerName();
			for(String name: customerNames){
				LOGGER.info(name);
			}
			LOGGER.info("\n");
		}catch(Exception e){
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info( message);
		}
	}
	
	public void getCustomerDetailsWhereV1() {
		try {
			List<CustomerDTO> customerDTOs = service.getCustomerDetails(1001);
			for(CustomerDTO customerDTO: customerDTOs) {
				LOGGER.info(customerDTO);
			}
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getCustomerDetailsWhereV2() {
		try {
			List<CustomerDTO> customerDTOs = service.getCustomerDetailsV1(1001);
			for(CustomerDTO customerDTO: customerDTOs) {
				LOGGER.info(customerDTO);
			}
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getCustomterDetailsWhereClauses() {
		try {
			List<CustomerDTO> customerDTOs = service.getCustomerDetailsV2();
			for (CustomerDTO customerDTO : customerDTOs) {
				LOGGER.info(customerDTO);
			}
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getMaximumBalance() {
		Integer maxBalance;
		try {
			maxBalance = service.getMaximumBalance();
			LOGGER.info(environment.getProperty("UserInterface.MAX_BALANCE") + maxBalance);
			LOGGER.info("\n");
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getCityWiseCustomerCount() {
		try {
			List<Object[]> objects = service.getCustomerCountForCities();
			for(Object [] object: objects) {
				LOGGER.info(object[0] + " " + object[1]);
			}
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getMinimumBalance() {
		Integer minBalance;
		try {
			minBalance = service.getMinimumBalance();
			LOGGER.info(environment.getProperty("UserInterface.MIN_BALANCE") + minBalance);
			LOGGER.info("\n");
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getNumberOfAccounts() {
		Long numberOfAccounts;
		try {
			numberOfAccounts = service.getNumberOfAccounts();
			LOGGER.info(environment.getProperty("UserInterface.NO_OF_ACCOUNTS") + numberOfAccounts);
			LOGGER.info("\n");
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getTotalBalance() {
		Long totalBalance;
		try {
			totalBalance = service.getTotalBalance();
			LOGGER.info(environment.getProperty("UserInterface.TOTAL_BALANCE") + totalBalance);
			LOGGER.info("\n");
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void getAverageBalance() {
		Double averageBalance;
		try {
			averageBalance = service.getAverageBalance();
			LOGGER.info(environment.getProperty("UserInterface.AVERAGE_BALANCE") + averageBalance);
			LOGGER.info("\n");
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void updateCityOfEmployee() {
		try {
			service.updateCityOfCustomer(1002, "Seattle");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
			LOGGER.info("\n");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	public void deleteCustomer() {
		try {
			Integer deleteCount = service.deleteCustomer();
			LOGGER.info(deleteCount + " " + environment.getProperty("UserInterface.DELETE_SUCCESS"));
			LOGGER.info("\n");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
}
