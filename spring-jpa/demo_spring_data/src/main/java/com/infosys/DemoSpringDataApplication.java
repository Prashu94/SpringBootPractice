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
import com.infosys.entity.Customer;
import com.infosys.repository.CustomerRepository;
import com.infosys.service.CustomerService;

@SpringBootApplication
public class DemoSpringDataApplication implements CommandLineRunner{
	private static final Log LOGGER = LogFactory.getLog(DemoSpringDataApplication.class);
	
	//@Autowired
	//CustomerRepository customerRepository;
	@Autowired
	CustomerService customerService;
	@Autowired
	Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		/*Customer customer1 = new Customer(1003, "joey@infy.com", "Joey", "New York", LocalDate.of(1987, 4, 2));
		Customer customer2 = new Customer(1004,	"allen@infy.com", "Allen", "Iowa", LocalDate.of(1980, 4, 2));
		
		// save customers
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		
		// fetch customer by id
		LOGGER.info("Customer fetched by findById(1)");
		LOGGER.info("-------------------------------");
		
		Customer customer3 = customerRepository.findById(1001).get();
		LOGGER.info(customer3);
		
		// fetching all customers
		LOGGER.info("Customers fetched by findAll()");
		LOGGER.info("-------------------------------");
		Iterable<Customer> customers = customerRepository.findAll();
		customers.forEach(LOGGER::info);*/
		
		// Demo Operations
		//addCustomer();
		getCustomer();
		findAllCustomers();
		updateCustomer();
		deleteCustomer();
	}
	
	public void addCustomer() {
		CustomerDTO customer = new CustomerDTO();
		customer.setCity("LA");
		customer.setCustomerId(1005);
		customer.setDateOfBirth(LocalDate.of(1990, 4, 22));
		customer.setEmailId("harry@infy.com");
		customer.setName("Harry");
		try {
			customerService.addCustomer(customer);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		}catch(Exception e) {
			if(e.getMessage()!=null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void getCustomer() {
		try {
			CustomerDTO customerDTO = customerService.findById(1001);
			LOGGER.info(customerDTO);
		}catch(Exception e) {
			if(e.getMessage()!=null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void findAllCustomers() {
		try {
			customerService.findAll().forEach(LOGGER::info);
		}catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}
	
	public void updateCustomer() {
		try {
			customerService.updateCustomer(1005, "harry01@infy.com");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}
	
	public void deleteCustomer() {
		try {
			customerService.deleteCustomer(1005);
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

}
