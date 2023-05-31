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
public class Payment {
    // My Account
    private long sourceId;
    // Account that receives Money
    private long  accountId;
    private String accountName;
    private long amount;
    private String reference;
    private String status;
    private String reasonCode;
    private Instant createdAt;
}
