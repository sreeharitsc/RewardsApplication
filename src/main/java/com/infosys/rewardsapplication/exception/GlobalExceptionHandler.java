package com.infosys.rewardsapplication.exception;

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

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleRewardsException(ResourceNotFoundException ex){
		RewardsException response = new RewardsException("errorMsg", ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<?> handleInvalidDateFormatException(InvalidDateFormatException ex){
		RewardsException response = new RewardsException("errorMsg", ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidDateInputException.class)
	public ResponseEntity<?> handleDateParseException(InvalidDateInputException ex){
		RewardsException response = new RewardsException("errorMsg", ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleOtherException(Exception ex){
		RewardsException response = new RewardsException("errorMsg","internal server error");
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
