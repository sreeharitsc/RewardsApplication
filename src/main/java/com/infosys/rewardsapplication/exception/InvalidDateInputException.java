/**
 * 
 */
package com.infosys.rewardsapplication.exception;

/**
 * @author sreehari
 * class used to represent exception for invalid input Dates like startDate ≤ endDate
 */
public class InvalidDateInputException extends RuntimeException{

	public InvalidDateInputException(String message) {
		super(message);
	}
}
