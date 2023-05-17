package com.bank.notificationservice.entity;

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
@Table(name = "LOAN_TYPE")
public class LoanType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer loanTypeId;
	private String loanType;
	private Double intrestRate;
	

}
