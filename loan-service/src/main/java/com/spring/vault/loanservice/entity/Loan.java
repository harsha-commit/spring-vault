package com.spring.vault.loanservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Loan {
    /*
        loan_id - unique ID for the loan application
        account_id - ID of the account which applied for the loan
        loan_type - type of loan (e.g. personal loan, home loan)
        loan_amount - the amount of the loan requested by the customer
        interest_rate - the interest rate for the loan
        status - the status of the loan application (e.g. pending, approved, rejected)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long accountId;
    private long amount;
    private long interest;
    private String status;
}
