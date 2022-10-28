package com.prospring5;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prospring5.dao.CustomerDao;
import com.prospring5.dao.CustomerDaoImpl;
import com.prospring5.entities.Customer;

public class CustomerJdbcDemo {
	private static CustomerDao customerDao = new CustomerDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(CustomerJdbcDemo.class);
	
	public static void main(String[] args) {
		logger.info("Get all the customers");
		listAllCustomers();
		
		logger.info("Insert new Customers");
		Customer customer1= new Customer(9009009009L, "Debashis", 27, 'M', "BBSR", 1);
		Customer customer2= new Customer(9009009010L, "Robert", 27, 'M', "PUNE", 2);
		Customer customer3= new Customer(9009009011L, "Lucy", 27, 'F', "MUMBAI", 3);
		
		customerDao.insert(customer1);
		customerDao.insert(customer2);
		customerDao.insert(customer3);
		
		logger.info("Get all the customers after insertion");
		listAllCustomers();
		
		
	}
	
	private static void listAllCustomers() {
		List<Customer> customers = customerDao.findAll();
		
		for(Customer customer: customers) {
			logger.info("Customer : "+ customer.toString());
		}
	}
}
