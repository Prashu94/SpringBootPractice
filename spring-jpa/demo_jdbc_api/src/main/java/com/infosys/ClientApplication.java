package com.infosys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.infosys.dto.CustomerDTO;
import com.infosys.service.CustomerService;
import com.infosys.service.CustomerServiceImpl;

/**
 * Hello world!
 *
 */
public class ClientApplication 
{   
    private static Logger logger = Logger.getLogger(ClientApplication.class);
    public static void main( String[] args ) throws FileNotFoundException
    {   
        /*CustomerDTO customer = new CustomerDTO(7022713722L, "Lucy", 27, 'F', "INDIA", 3);*/
		CustomerService customerService = new CustomerServiceImpl();
		Scanner scanner = new Scanner(System.in);
		/*
		System.out.println(readFromCSV("spring-jpa\\demo_jdbc_api\\src\\main\\resources\\CustomerData.csv"));
		*/
		List<CustomerDTO> customerDTOS = readFromCSV("spring-jpa\\demo_jdbc_api\\src\\main\\resources\\CustomerData.csv");

		// bulk insert
		insertCustomerData(customerService, customerDTOS);

		logger.info("Check records after inserting the customer records");
		
		listCustomerDTO(customerService.findAll());

		logger.info("Enter the phone number you want to find: ");
		Long phoneNoSearch = scanner.nextLong();
		CustomerDTO customerDTOSearch = customerService.findByPhoneNumber(phoneNoSearch);
		logger.info(customerDTOSearch);

		logger.info("Records are successfully added..");
		logger.info("Enter the phone Number of the Customer which has to be deleted.");
		
		Long phoneNo = scanner.nextLong();
		int result = customerService.remove(phoneNo);
		if (result == 1) {
			logger.info("Success : Record deleted successfully ");
		} else {
			logger.info("Error : No record found for the given phone number ");
		}

		
		scanner.close();
		
    }

	public static List<CustomerDTO> readFromCSV(String filename){
		List<CustomerDTO> customer = new ArrayList<>();
		Path pathToFile = Paths.get(filename);

		try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)){
			// read the first line of the csv file
			String line = br.readLine();
			// loop until all the lines are read
			while(line!=null){
				String [] attributes = line.split(",");

				CustomerDTO customerDTO = createCustomer(attributes);

				// adding the customers
				customer.add(customerDTO);

				// read the next line before looping 
				line = br.readLine();
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return customer;
	}

	private static CustomerDTO createCustomer(String [] metadata){
		return new CustomerDTO(Long.parseLong(metadata[0]), 
		metadata[1], 
		Integer.parseInt(metadata[2]), 
		metadata[3].charAt(0), 
		metadata[4], 
		Integer.parseInt(metadata[5]));

	}

	public static void insertCustomerData(CustomerService service, List<CustomerDTO> customerDTO){
		for(CustomerDTO customer: customerDTO){
			service.insert(customer);
		}
	}

	private static void listCustomerDTO(List<CustomerDTO> customerDTO){
		for(CustomerDTO customer: customerDTO){
			logger.info(customer);
		}
	}
}
