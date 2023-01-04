package com.infytel.exceptions;

import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.infytel.dto.ErrorMessage;
 
@RestControllerAdvice
public class ExceptionControllerAdvice {
 
	@ExceptionHandler(Exception.class)
	public String  exceptionHandler(Exception ex) {
		 
		return  ex.getMessage();
	}
	
	
		//Handler for NoSuchCustomerException
		@ExceptionHandler(NoSuchCustomerException.class)
		public ResponseEntity<ErrorMessage> exceptionHandler2(NoSuchCustomerException ex) 
		{
			 ErrorMessage error = new ErrorMessage();
		     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		     error.setMessage(ex.getMessage());
		     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		
		//Handler that handles the exception raised because of invalid data that is received as method argument (DTO)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) 
		{
			 ErrorMessage error = new ErrorMessage();
		     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		     error.setMessage(ex.getBindingResult().getAllErrors()
		    		 		  	.stream().map(ObjectError::getDefaultMessage)//lambda equivalent -> x->x.getDefaultMessage()
		    		 		  	.collect(Collectors.joining(", ")));
		        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		

} 