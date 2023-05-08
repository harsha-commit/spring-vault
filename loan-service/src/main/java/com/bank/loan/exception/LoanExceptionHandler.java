package com.bank.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.loan.binding.ExceptionResponse;

@ControllerAdvice
public class LoanExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LoanException.class)
	public ResponseEntity<ExceptionResponse> customExceptionHandler(LoanException loanException) {
		ExceptionResponse build = ExceptionResponse.builder().errorCode(loanException.getMessage())
				.errorMessage(loanException.getMessage()).build();

		return new ResponseEntity<ExceptionResponse>(build, HttpStatus.valueOf(loanException.errorCode));

	}
}
