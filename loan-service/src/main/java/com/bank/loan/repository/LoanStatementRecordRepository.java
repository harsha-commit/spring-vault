package com.bank.loan.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.loan.entity.LoanStatementEntity;

public interface LoanStatementRecordRepository extends JpaRepository<LoanStatementEntity, Integer> {

	List<LoanStatementEntity> findByCustomerId(Integer customerId);

	List<LoanStatementEntity> findByLoanIdAndCustomerId(Integer loanId, Integer customerId);

	List<LoanStatementEntity> findByLoanIdAndCustomerIdAndThisMonth(Integer loanId, Integer customerId, String month);

	LoanStatementEntity findByLoanIdAndCustomerIdAndNextDueDate(Integer loanId, Integer customerId, LocalDate dueDate);

	// LoanStatementEntity findByCustomerIdAndloanStmtId(Integer custId, Integer
	// stmtId);

	// @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
	// User findUserByStatusAndName(Integer status, String name);

	@Query(value = "select l from LoanStatementEntity l WHERE l.customerId = ?1 and l.loanId = ?2 and l.closeAmount>0")
	public LoanStatementEntity closeAmount(Integer customerId, Integer loanId);

	@Query(value = "select l from LoanStatementEntity l WHERE l.customerId = ?1 and l.loanId = ?2 and l.closeAmount=null and l.paidDate=null")
	public List<LoanStatementEntity> clearStatement(Integer customerId, Integer loanId);

	@Query(value = "select l.closeAmount from LoanStatementEntity l WHERE l.customerId = ?1 and l.loanId = ?2 and l.closeAmount>0")
	public Double getCloseAmount(Integer customerId, Integer loanId);
	// findByStartDateBetween();

}
