package com.infytel.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infytel.dto.CustomerDTO;
import com.infytel.exceptions.NoSuchCustomerException;
import com.infytel.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
 

@RestController
@RequestMapping("/customers")
@Api(value = "CustomerController, REST APIs that deal with Customer DTO")
public class CustomerController 
{
	
	
	@Autowired
	private CustomerService customerService;
	
	//Fetching customer details
	@GetMapping(produces="application/json")
	@ApiOperation(value = "Fetch all the customers of Infytel", response = CustomerDTO.class, tags="fetchCustomer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fetched the customers successfully"),
							 @ApiResponse(code = 404, message = "Customer details not found") })
	public  List<CustomerDTO> fetchCustomer()
	{
		return customerService.fetchCustomer();
	}
		
	//Adding a customer
	@PostMapping(consumes="application/json")
	public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) 
	{
		return ResponseEntity.ok(customerService.createCustomer(customerDTO));
	}

	//Updating an existing customer
	@PutMapping(value = "/{phoneNumber}", consumes = "application/json")
	public String updateCustomer(@PathVariable("phoneNumber") long phoneNumber, @RequestBody CustomerDTO customerDTO) 
	{
		return customerService.updateCustomer(phoneNumber, customerDTO);
	}
	

	// Deleting a customer
	@DeleteMapping(value="/{phoneNumber}",produces="text/html")
	public String deleteCustomer(@PathVariable("phoneNumber") long phoneNumber)
			throws NoSuchCustomerException {

		return customerService.deleteCustomer(phoneNumber);

	}
	
}
