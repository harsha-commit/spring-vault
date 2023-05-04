package com.bank.loan.entity;

import java.time.LocalDate;
import java.time.Month;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOAN_STATEMENT_RECORD")
public class LoanStatementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOAN_STMT_ID")
	private Integer loanStmtId;
	
	@Column(name = "CUSTOMER_ID")
	private Integer customerId;
	
	@Column(name = "LOAN_ID")
	private Integer loanId;
	
	@Column(name = "PRINCIPAL_AMOUNT")
	private Double principalAmount;
	
	@Column(name = "CLOSE_AMOUNT")	
	private Double closeAmount;
	
	@Column(name = "MONTH_PAID_AMOUNT")	
	private Double monthPaidAmount;
	
	@Column(name = "MONTH")
	private String thisMonth;
	
	@Column(name = "PAID_DATE")
	private LocalDate paidDate;
	
	@Column(name = "NEXT_DUE_DATE")
	private LocalDate nextDueDate;
	
	@Column(name = "DEFAULTER")
	private String defaulter;
	@Column(name = "BOUNCE")	
	private Boolean bounce;
	@Column(name = "BOUNCE_PLANTY")	
	private Double bouncePlanty;
	
	@Column(name = "INTEREST_MONTH_AMOUNT")
	private Double intrestMonthAmount;
	@Column(name = "EMI_LEFT")
	private Integer emiLeft;
	
	
}
