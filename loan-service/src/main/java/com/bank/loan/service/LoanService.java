package com.bank.loan.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.bank.loan.binding.Loan;
import com.bank.loan.binding.LoanStatement;

public interface LoanService {

	// loanType
	public Map<Integer, String> dropDownLoanType();

	public String loanApply(Loan loan);

	public List<Loan> viewLoan(Integer custId);

	public List<LoanStatement> repaymentStmt(Integer custId, Integer loanId);
	
	public String loanVarification(Integer custId, Integer loanId);
	
	
}
