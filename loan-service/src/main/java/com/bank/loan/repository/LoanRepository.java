package com.bank.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.loan.entity.LoanEntity;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {// CurdRepository<LoanEntity, Integer>{
	// customerId loanId
	LoanEntity findByLoanIdAndCustomerId(Integer loanId, Integer customerId);

	// LoanEntity findByLoanIdAndCustomerId();
	//LoanEntity findByLoanIdAndCustomerId(Integer loanId, Integer b);
	List<LoanEntity> findByCustomerId(Integer custId);
}
