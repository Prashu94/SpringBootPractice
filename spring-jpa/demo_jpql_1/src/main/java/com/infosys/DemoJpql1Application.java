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
}
