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
@Table(name = "payment_db")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "SOURCE_ID")
    private long sourceId;

    @Column(name = "ACCOUNT_ID")
    private long  accountId;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @Column(name = "AMOUNT")
    private long amount;

    @Column(name = "REFERENCE")
    private String reference;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REASON_CODE")
    private String reasonCode;

    @Column(name = "CREATED_AT")
    private Instant createdAt;
}
