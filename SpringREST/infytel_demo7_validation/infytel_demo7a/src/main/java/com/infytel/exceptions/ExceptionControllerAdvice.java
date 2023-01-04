package com.infytel.exceptions;


import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.infytel.dto.ErrorMessage;
import com.infytel.util.InfyTelConstants;
 
@RestControllerAdvice

public class ExceptionControllerAdvice {
	//this helps receiving the message/value related to the general exception from the ValidationMessages.properties
	@Autowired
	private Environment environment;
 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exceptionHandler2(Exception ex) {
		 ErrorMessage error = new ErrorMessage();
	     error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	     error.setMessage(environment.getProperty(InfyTelConstants.GENERAL_EXCEPTION_MESSAGE.toString()));
	     return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		 
	}
	
	
	@ExceptionHandler(NoSuchCustomerException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler2(NoSuchCustomerException ex) {
		 ErrorMessage error = new ErrorMessage();
	        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	        error.setMessage(ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		 
	}
	
	//Handler for validation failures w.r.t DTOs
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		 ErrorMessage error = new ErrorMessage();
	        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	        error.setMessage(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
	        		.collect(Collectors.joining(", ")));
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	//Handler for validation failures w.r.t URI parameters
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(
			ConstraintViolationException ex) {
		
		 
		
		 ErrorMessage error = new ErrorMessage();
	        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	        error.setMessage(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
	        		.collect(Collectors.joining(", ")));
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    
	}
	
	
	
} 