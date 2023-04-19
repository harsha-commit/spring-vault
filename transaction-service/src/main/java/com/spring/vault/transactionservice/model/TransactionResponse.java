package com.spring.vault.transactionservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private long id;
    private long accountId;
    private TransactionType transactionType;
    private long amount;
    private Instant date;
}
