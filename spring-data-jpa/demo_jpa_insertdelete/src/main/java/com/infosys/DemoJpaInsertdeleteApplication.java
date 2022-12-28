package com.infosys;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;
import com.infosys.service.CustomerService;

@SpringBootApplication
public class DemoJpaInsertdeleteApplication implements CommandLineRunner {
	public static final Logger LOGGER = LogManager.getLogger(DemoJpaInsertdeleteApplication.class);

	@Autowired
	AbstractApplicationContext context;

	@Autowired
	CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaInsertdeleteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CustomerService customerService = (CustomerService) context.getBean("customerService");

		Scanner scanner = new Scanner(System.in);

		// read the csv file
		List<CustomerDTO> customerDTOS = readFromCSV("src\\main\\resources\\CustomerData.csv");

		// bulk insert
		insertCustomerData(customerService, customerDTOS);
		
		LOGGER.info("Check records after inserting the customer records");
		
		listCustomerDTO(customerService.findAll());
		
		LOGGER.info("Enter the phone Number of the Customer which has to be deleted.");

		Long phoneNo = scanner.nextLong();
		customerService.removeCustomer(phoneNo);
		
		LOGGER.info("Let us update the customer's address");
		LOGGER.info("Enter the phone number of the customer to be updated");
		Long phoneNo1 = scanner.nextLong();
		LOGGER.info("Enter the new planId ");
		Integer planId = scanner.nextInt();
		customerService.updateCustomer(phoneNo1, planId);
		LOGGER.info("Customer's address updated successfully");

		scanner.close();
		context.close();
	}

	public static List<CustomerDTO> readFromCSV(String filename) {
		List<CustomerDTO> customer = new ArrayList<>();
		Path pathToFile = Paths.get(filename);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			// read the first line of the csv file
			String line = br.readLine();
			// loop until all the lines are read
			while (line != null) {
				String[] attributes = line.split(",");

				CustomerDTO customerDTO = createCustomer(attributes);

				// adding the customers
				customer.add(customerDTO);

				// read the next line before looping
				line = br.readLine();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return customer;
	}

	public static CustomerDTO createCustomer(String[] metadata) {
		return new CustomerDTO(Long.parseLong(metadata[0]), metadata[1], Integer.parseInt(metadata[2]),
				metadata[3].charAt(0), metadata[4], Integer.parseInt(metadata[5]));
	}

	public static void insertCustomerData(CustomerService service, List<CustomerDTO> customerDTO) {
		for (CustomerDTO customer : customerDTO) {
			try {
				service.insertCustomer(customer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void listCustomerDTO(List<Customer> customer) {
		for (Customer cust : customer) {

			LOGGER.info(Customer.prepareCustomerDTO(cust));
		}
	}

}
