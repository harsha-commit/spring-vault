package com.bank.loan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loan.binding.Loan;
import com.bank.loan.binding.Payment;
import com.bank.loan.service.LoanService;

@RestController
@RequestMapping("loan")
public class LoanController {

	@Autowired
	LoanService loanService;

	// dropdown
	public ResponseEntity<Map<Integer, String>> dropDownLoanType() {
		return new ResponseEntity<Map<Integer, String>>(loanService.dropDownLoanType(), HttpStatus.OK);
	}

	// apply loan
	@PostMapping("/apply")
	public ResponseEntity<String> loanApply(@RequestBody Loan loan) {
		return new ResponseEntity<>(loanService.loanApply(loan), HttpStatus.CREATED);
	}

	// loan veriy by Worker
	@GetMapping("/verify/{custId}/{loanId}")
	public ResponseEntity<String> verifyingLoan(@PathVariable Integer custId, @PathVariable Integer loanId) {
		return new ResponseEntity<>(loanService.loanVarification(custId, loanId), HttpStatus.OK);
	}

	// get Apply Loan List by Particular Customer
	@GetMapping("/appliedLoan/{custId}")
	public ResponseEntity<List<Loan>> getLoanByCustId(@PathVariable Integer custId) {
		return new ResponseEntity<>(loanService.viewLoan(custId), HttpStatus.OK);
	}

	@PutMapping("/dofullpayment")
	public ResponseEntity<?> doFullPayment(@RequestBody Payment payment) {

		return new ResponseEntity<>(loanService.doFullPayment(payment), HttpStatus.OK);
	}

	@PutMapping("/dofullpayment")
	public ResponseEntity<?> doMonthlyPayment(@RequestBody Payment payment) {

		return new ResponseEntity<>(loanService.monthlyRepayment(payment), HttpStatus.OK);

	}

	@GetMapping("/statement/{custId}/{LoanId}")
	public ResponseEntity<?> getUpdatedStatement(@PathVariable Integer custId, @PathVariable Integer loanId) {

		return new ResponseEntity<>(loanService.repaymentStmt(custId, loanId), HttpStatus.OK);

	}
}
