package com.bank.loan.binding;

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
public class LoanStatement {

	private Integer loanStmtId;

	private Integer customerId;

	private Integer loanId;
	private Double principalAmount;
	private Double closeAmount;
	private Double monthPaidAmount;
	private String thisMonth;
	private LocalDate paidDate;
	private LocalDate nextDueDate;
	private String defaulter;
	private Boolean bounce;
	private Double bouncePlanty;
	private Double intrestMonthAmount;
	private Integer emiLeft;

}
