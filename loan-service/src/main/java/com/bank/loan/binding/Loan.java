package com.bank.loan.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
		
	private Integer customerId;
	private Integer loanTypeId;
	private Double loanAmount;
	private Integer duration;
	private String status;
	private LocalDate appliedDate;
	private LocalDate startDate;
	private LocalDate endDate;
	
}
