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
import com.infosys.service.CustomerService;

@SpringBootApplication
public class DemoSpringOrmJpaCrudApplication implements CommandLineRunner {
	public static final Logger logger = LogManager.getLogger(DemoSpringOrmJpaCrudApplication.class);

	@Autowired
	AbstractApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringOrmJpaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CustomerService customerService = (CustomerService) context.getBean("customerService");
		
		CustomerDTO customer1 = new CustomerDTO(9009009009L, "Debashis", 27, 'M', "BBSR", 1);
		CustomerDTO customer2 = new CustomerDTO(9009009010L, "Robert", 27, 'M', "PUNE", 2);
		CustomerDTO customer3 = new CustomerDTO(9009009011L, "Lucy", 27, 'F', "MUMBAI", 3);

		customerService.insert(customer1);
		customerService.insert(customer2);
		customerService.insert(customer3);
		logger.info("Records are successfully added..");

		System.out.println("Enter the phone Number of the Customer which has to be deleted.");
		Scanner scanner = new Scanner(System.in);
		Long phoneNo = scanner.nextLong();
		// Invoking Service layer method to remove Customer details from
		// Customer table
		int result = customerService.remove(phoneNo);
		if (result == 1) {
			logger.info("Success : Record deleted successfully ");
		} else {
			logger.info("Error : No record found for the given phone number ");
		}
		logger.info("Viewing All Customer Details");
		ArrayList<CustomerDTO> cList = (ArrayList<CustomerDTO>) customerService.getAll();
		for (CustomerDTO customer : cList) {
			logger.info(customer);
		}

		logger.info("Display completed");
		logger.info("");
		logger.info("Let's update a Customer with new Address details");
		System.out.println("Enter the phone number of the Customer to be updated:");
		Scanner sc = new Scanner(System.in);
		Long phoneNo1 = sc.nextLong();
		System.out.println("Enter new Address");
		String newAddress = sc.next();
		customerService.update(phoneNo1, newAddress);
		logger.info("Customer's address updated successfully!");
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
			service.insert(customer);
		}
	}

	private static void listCustomerDTO(List<CustomerDTO> customerDTO) {
		for (CustomerDTO customer : customerDTO) {

			logger.info(customer);
		}
	}

}
