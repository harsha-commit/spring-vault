package com.spring.vault.transactionservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "ACCOUNT_ID")
    private long accountId;
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
    @Column(name = "AMOUNT")
    private long amount;
    @Column(name = "DATE")
    private Instant date;
}
