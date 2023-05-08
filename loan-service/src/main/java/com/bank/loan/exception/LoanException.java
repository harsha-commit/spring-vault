package com.bank.loan.exception;

public class LoanException extends RuntimeException {

	String errorMessage;
	String errorStatus;
	int errorCode;
	public LoanException(String errorMessage, String errorStatus,int errorCode) {
		super(errorMessage);
		
		this.errorMessage = errorMessage;
		this.errorStatus = errorStatus;
		this.errorCode = errorCode;
	}
	
	
}
