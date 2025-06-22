/**
 * 
 */
package com.infosys.rewardsapplication.exception;

/**
 * @author sreehari
 * class used to represent Resource Not Found exception
 */
public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
