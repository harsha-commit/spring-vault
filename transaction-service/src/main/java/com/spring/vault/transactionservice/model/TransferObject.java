package com.spring.vault.transactionservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferObject {
    private long sourceId;
    private long destinationId;
    private String sourceAccountName;
    private String destinationAccountName;
}
