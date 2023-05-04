package com.bank.loan.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "LOAN")
public class LoanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "LOAN_ID")
	private Integer loanId;

	@Column(name = "CUST_ID", nullable = false)
	private Integer customerId;

	@Column(name = "LOAN_TYPE_ID")
	private Integer loanTypeId;

	@Column(name = "LOAN_AMOUNT")
	private Double loanAmount;

	@Column(name = "DURATION")
	private Integer duration;

	@Column(name = "INTREST_RATE")
	private Double intrestRate;
	
	@Column(name = "INTREST_AMOUNT")
	private Double intrestAmount;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "START_DATE")
	private LocalDate startDate;
	@Column(name = "END_DATE")
	private LocalDate endDate;

	@Column(name = "APPLIED_ON", updatable = false)
	@CreationTimestamp
	private LocalDate appliedOn;

	@Column(name = "BANK_WORKER_ID")
	private Integer bankWorkerId;

	@Column(name = "STATUS_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate statusDate;
}
