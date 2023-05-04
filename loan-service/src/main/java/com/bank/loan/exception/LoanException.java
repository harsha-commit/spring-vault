package com.bank.loan.exception;

public class LoanException extends RuntimeException {

	String errorMessage;
	String errorCode;
	public LoanException(String errorMessage, String errorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	
}
