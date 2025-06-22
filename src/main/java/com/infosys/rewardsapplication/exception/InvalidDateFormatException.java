/**
 * 
 */
package com.infosys.rewardsapplication.exception;

/**
 * @author sreehari
 * class used to represent exception for invalid input Date format other than like yyyy-MM-dd
 */
public class InvalidDateFormatException extends RuntimeException{

	public InvalidDateFormatException(String message) {
		super(message);
	}
}
