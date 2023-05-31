package com.spring.vault.transactionservice.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionMongo {
    @Id
    private String id;
    // Takes Transaction or Payment
    private Transaction transaction;
    private Payment payment;
}
