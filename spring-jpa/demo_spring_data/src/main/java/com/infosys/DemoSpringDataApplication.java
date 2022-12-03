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

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;
import com.infosys.repository.CustomerRepository;
import com.infosys.service.CustomerService;

@SpringBootApplication
public class DemoSpringDataApplication implements CommandLineRunner {
	private static final Log LOGGER = LogFactory.getLog(DemoSpringDataApplication.class);

	// @Autowired
	// CustomerRepository customerRepository;
	@Autowired
	CustomerService customerService;
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Customer customer1 = new Customer(1003, "joey@infy.com", "Joey", "New York",
		 * LocalDate.of(1987, 4, 2)); Customer customer2 = new Customer(1004,
		 * "allen@infy.com", "Allen", "Iowa", LocalDate.of(1980, 4, 2));
		 * 
		 * // save customers customerRepository.save(customer1);
		 * customerRepository.save(customer2);
		 * 
		 * // fetch customer by id LOGGER.info("Customer fetched by findById(1)");
		 * LOGGER.info("-------------------------------");
		 * 
		 * Customer customer3 = customerRepository.findById(1001).get();
		 * LOGGER.info(customer3);
		 * 
		 * // fetching all customers LOGGER.info("Customers fetched by findAll()");
		 * LOGGER.info("-------------------------------"); Iterable<Customer> customers
		 * = customerRepository.findAll(); customers.forEach(LOGGER::info);
		 */

		// Demo Operations
		// addCustomer();
		// getCustomer();
		// findAllCustomers();
		// updateCustomer();
		// deleteCustomer();

		// Query creation based on method name - Demo
		LOGGER.info("findByEmailId");
		findByEmailId();
		LOGGER.info("findByEmailIdAndName");
		findByEmailIdAndName();
		LOGGER.info("findByEmailIdOrName");
		findByEmailIdOrName();
		LOGGER.info("findByDateOfBirthBetween");
		findByDateOfBirthBetween();
		LOGGER.info("findByDateOfBirthLessThan");
		findByDateOfBirthLessThan();
		LOGGER.info("findByDateOfBirthGreaterThan");
		findByDateOfBirthGreaterThan();
		LOGGER.info("findByDateOfBirthAfter");
		findByDateOfBirthAfter();
		LOGGER.info("findByDateOfBirthBefore");
		findByDateOfBirthBefore();
		LOGGER.info("findByEmailIdIsNull");
		findByEmailIdIsNull();
		LOGGER.info("findByNameLike");
		findByNameLike();
		LOGGER.info("findByNameOrderByDateOfBirth");
		findByNameOrderByDateOfBirth();
		LOGGER.info("findByNameOrderByDateOfBirthDesc");
		findByNameOrderByDateOfBirthDesc();
		
		// Query creation based on @Query Annotation
		LOGGER.info("findNameByEmailId");
		findNameByEmailId();
		//LOGGER.info("updateCustomerEmailId");
		//updateCustomerByEmailId();
		//LOGGER.info("deleteCustomerByEmailId");
		//deleteCustomerByEmailId();
		
		// Query Creation using @NamedQuery.
		LOGGER.info("findNameByEmailIdV1");
		findNameByEmailIdV1();
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
		} catch (Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}

	public void getCustomer() {
		try {
			CustomerDTO customerDTO = customerService.findById(1001);
			LOGGER.info(customerDTO);
		} catch (Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}

	public void findAllCustomers() {
		try {
			customerService.findAll().forEach(LOGGER::info);
		} catch (Exception e) {
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

	public void findByEmailId() {
		try {
			CustomerDTO customerDTO = customerService.findByEmailId("martin@infy.com");
			LOGGER.info(customerDTO);
			LOGGER.info("\n");
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}
	
	
	public void findByEmailIdAndName() {
		try {
			CustomerDTO customerDTO = customerService.findByEmailIdAndName("martin@infy.com", "martin");
			LOGGER.info(customerDTO);
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByEmailIdOrName() {
		try {
			List<CustomerDTO> customerDTOs = customerService.findByEmailIdOrName("martin@infy.com", "martin");
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	
	public void findByDateOfBirthBetween() {
		try {
			LocalDate fromDate = LocalDate.of(1995, 1, 1);
			LocalDate toDate = LocalDate.of(2000, 12, 31);
			List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthBetween(fromDate, toDate);
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByDateOfBirthLessThan() {
		try {
			LocalDate dateOfBirth = LocalDate.of(2000, 12, 31);
			List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthLessThan(dateOfBirth);
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByDateOfBirthGreaterThan() {
		try {
			LocalDate dateOfBirth = LocalDate.of(1995, 12, 31);
			List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthGreaterThan(dateOfBirth);
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByDateOfBirthAfter() {
		try {
			LocalDate dateOfBirth = LocalDate.of(1995, 12, 31);
			List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthAfter(dateOfBirth);
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByDateOfBirthBefore() {
		try {
			LocalDate dateOfBirth = LocalDate.of(2000, 12, 31);
			List<CustomerDTO> customerDTOs = customerService.findByDateOfBirthBefore(dateOfBirth);
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByEmailIdIsNull() {
		try {
			List<CustomerDTO> customerDTOs = customerService.findByEmailIdNull();
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByNameLike() {
		try {
			List<CustomerDTO> customerDTOs = customerService.findByNameLike("martin");
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByNameOrderByDateOfBirth() {
		try {
			List<CustomerDTO> customerDTOs = customerService.findByNameOrderByDateOfBirth("martin");
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findByNameOrderByDateOfBirthDesc() {
		try {
			List<CustomerDTO> customerDTOs = customerService.findByNameOrderByDateOfBirthDesc("martin");
			customerDTOs.forEach(customerDTO -> {
				LOGGER.info(customerDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findNameByEmailId() {
		try {
			String customerName = customerService.findByNameByEmailId("john@infy.com");
			LOGGER.info("Customer Name: " + customerName);
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void updateCustomerByEmailId() {
		try {
			customerService.updateCustomerEmailId("tim@infy.com", 5);
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void deleteCustomerByEmailId() {
		try {
			customerService.deleteCustomerByEmailId("martin01@infy.com");
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		}catch(Exception e) {
			if(e.getMessage()!=null)
				LOGGER.info(environment.getProperty(e.getMessage(), 
							"Something went wrong. Please check log file for more details"));
		}
	}
	
	public void findNameByEmailIdV1() {
		try {
			String name = customerService.findNameByEmailId("martin@infy.com");
			LOGGER.info("Customer name : " + name);
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}
}
