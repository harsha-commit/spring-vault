package com.spring.vault.transactionservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    private long customerId;
    private String accountName;
    private String transactionType;
    private long amount;
    private String status;
    private String reasonCode;
    private Instant createdAt;
}
