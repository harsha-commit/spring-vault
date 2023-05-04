package com.bank.loan.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

	private Integer customerId;
	private Integer loanId;
	private Double amount;
	private LocalDate dueDate;
}
