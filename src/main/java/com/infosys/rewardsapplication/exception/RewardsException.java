package com.infosys.rewardsapplication.exception;

/**
 * @author sreehari
 * class used to represent the exceptions for rewards
 */
public class RewardsException extends RuntimeException{

	public RewardsException(String message) {
		super(message);
	}
	
	private String errorCode;
	private String errorMessage;
	
	
	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public RewardsException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
