package com.example.utility;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.CustomerException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	private Environment env;
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
        ErrorInfo error=new ErrorInfo();
		error.setErrorMessage(env.getProperty("General.EXCEPTION_MESSAGE"));
		error.setErrorNo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);			
	}

	
	@ExceptionHandler(CustomerException.class)
	//public ResponseEntity<String> exceptionHandler1(CustomerException exception)
	
	public ResponseEntity<ErrorInfo> exceptionHandler1(CustomerException exception)
	{
		  ErrorInfo error=new ErrorInfo();
			error.setErrorMessage(env.getProperty(exception.getMessage()));
			error.setErrorNo(HttpStatus.NOT_FOUND.value());
			error.setTimeStamp(LocalDateTime.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);	
	    //  return new ResponseEntity(env.getProperty(exception.getMessage()),HttpStatus.NOT_FOUND);
	}

}
