package com.infosys.rewardsapplication.exception;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author sreehari
 * class used to represent global exception handling
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RewardsException.class)
	public ResponseEntity<?> handleRewardsException(RewardsException ex){
		Map<String, String> response = new HashMap<>();
		response.put("error",ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<?> handleDateParseException(DateTimeParseException ex){
		Map<String, String> response = new HashMap<>();
		response.put("error","invalid date format, please use yyyy-MM-dd format");
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleOtherException(Exception ex){
		Map<String, String> response = new HashMap<>();
		response.put("error","internal server error");
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
