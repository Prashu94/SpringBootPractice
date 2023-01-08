package com.infosys.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.dto.CustomerDTO;
import com.infosys.dto.ErrorMessage;
import com.infosys.exceptions.NoSuchCustomerException;
import com.infosys.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	// CustomerController needs to contact CustomerService, hence dependency
	// injecting the
	// customerService reference
	@Autowired
	private CustomerService customerService;

	// Fetch the customerDetails
	@GetMapping(produces = "application/json")
	public List<CustomerDTO> fetchCustomer() {
		// This method will return the customers of InfyTel
		return customerService.fetchCustomer();
	}

	// Adding a customer
	@PostMapping(consumes = "application/json")
	public ResponseEntity createCustomer(@Valid @RequestBody CustomerDTO customerDTO, Errors errors) {
		// This method will create a customer

		String response = "";
		if (errors.hasErrors()) {
			// collecting the validation errors of all fields together in a string delimited
			// by commas
			response = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			ErrorMessage error = new ErrorMessage();
			error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
			error.setMessage(response);
			return ResponseEntity.ok(error);
		} else {
			response = customerService.createCustomer(customerDTO);
			return ResponseEntity.ok(response);
		}

	}

	// Updating an existing customer
	@PutMapping(value = "/{phoneNumber}", consumes = "application/json")
	public String updateCustomer(@PathVariable("phoneNumber") 
								 @Pattern(regexp = "[0-9]{10}",message="{customer.phoneNo.invalid}") String phoneNumber, 
								 @RequestBody CustomerDTO customerDTO)
			throws NumberFormatException, NoSuchCustomerException {
		// This method will update an existing customer
		return customerService.updateCustomer_1(Long.parseLong(phoneNumber), customerDTO);
	}

	// Delete mapping
	@DeleteMapping
	public String deleteCustomer() {
		// This method will delete an existing customer
		return "customer details deleted successfully";
	}

	// update an existing customer
	/*@PutMapping(value = "/{phoneNumber}", consumes = "application/json")
	public String updateCustomer(@PathVariable("phoneNumber") long phoneNumber, @RequestBody CustomerDTO customerDTO) {
		return customerService.updateCustomer(phoneNumber, customerDTO);
	}*/

	// Deleting the customer
	@DeleteMapping(value="/{phoneNumber}",produces="text/html")
	public String deleteCustomer(@PathVariable("phoneNumber") 
								 @Pattern(regexp = "[0-9]{10}",message="{customer.phoneNo.invalid}") 
								 String phoneNumber) throws NoSuchCustomerException {
		return customerService.deleteCustomer(Long.parseLong(phoneNumber));
	}
}
