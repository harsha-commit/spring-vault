package com.spring.vault.transactionservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction_db")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "AMOUNT")
    private long amount;

    @Column(name = "STATUS")
    private String status;
}
