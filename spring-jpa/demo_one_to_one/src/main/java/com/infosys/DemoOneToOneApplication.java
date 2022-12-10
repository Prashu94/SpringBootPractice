package com.infosys;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infosys.dto.AddressDTO;
import com.infosys.dto.CustomerDTO;
import com.infosys.service.AddressService;
import com.infosys.service.CustomerService;

@SpringBootApplication
public class DemoOneToOneApplication implements CommandLineRunner {
	public static final Log LOGGER = LogFactory.getLog(DemoOneToOneApplication.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoOneToOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("========================== GET CUSTOMER =================================");
		getCustomer();
		LOGGER.info("==========================  ADD CUSTOMER ================================");
		addCustomer();
		LOGGER.info("==========================  UPDATE CUSTOMER ================================");
		updateAddress();
		LOGGER.info("==========================  DELETE CUSTOMER ================================");
		deleteCustomer();
		LOGGER.info("==========================  DELETE CUSTOMER ONLY ================================");
		deleteCustomerOnly();
		LOGGER.info("==========================  ADD CUSTOMER FOR EXSITING ADDRESS ================================");
		addCustomerWithExistingAddress();
	}
	
	public void getCustomer() {
		try {
			CustomerDTO customerDTO = customerService.getCustomer(1234);
			LOGGER.info(customerDTO);
		}catch(Exception e) {
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void addCustomer() {
		try {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setName("Ron");
			customerDTO.setEmailId("ron@infy.com");
			customerDTO.setDateOfBirth(LocalDate.of(1993, 03, 24));
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setAddressId(103);
			addressDTO.setCity("Albany");
			addressDTO.setStreet("93 Taylor Road");
			customerDTO.setAddress(addressDTO);
			Integer customerId = customerService.addCustomer(customerDTO);
			LOGGER.info("\n" + environment.getProperty("UserInterface.CUSTOMER_ADDED") + customerId);
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occurred. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void updateAddress() {
		try {
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setCity("Rochester");
			addressDTO.setStreet("12 Tim Street");
			customerService.updateAddress(1234, addressDTO);
			LOGGER.info("\n" + environment.getProperty("UserInterface.CUSTOMER_UPDATED"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	
	public void deleteCustomer() {
		try {
			customerService.deleteCustomer(1234);
			LOGGER.info("\n" + environment.getProperty("UserInterface.CUSTOMER_ADDRESS_DELETED"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void deleteCustomerOnly() {
		try {
			customerService.deleteCustomerOnly(1235);
			LOGGER.info("\n" + environment.getProperty("UserInterface.CUSTOMER_DELETED"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
	
	public void addCustomerWithExistingAddress() {
		try {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setName("Harry");
			customerDTO.setEmailId("harry@infy.com");
			customerDTO.setDateOfBirth(LocalDate.of(1993, 03, 24));
			Integer addressId = 101;
			Integer customerId = addressService.addCustomerWithExistingAddress(addressId, customerDTO);
			LOGGER.info("\n" + environment.getProperty("UserInterface.CUSTOMER_ADDED") + customerId);
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occurred. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}
}
