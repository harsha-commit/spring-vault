package com.bank.exception;


public class LoanCustomException extends RuntimeException {

	private String errorMessage;
	private String errorStatus;
	private String errorCode;

	public LoanCustomException(String errorMessage, String errorStatus, String errorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.errorStatus = errorStatus;

	}

}
