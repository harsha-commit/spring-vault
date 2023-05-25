package com.spring.vault.accountservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "ACCOUNT_NAME")
    private String name;
    @Column(name = "CUSTOMER_ID")
    private long customerId;
    @Column(name = "BALANCE")
    private long balance;
    @Column(name = "ACCOUNT_TYPE")
    private String accountType; // checking, savings, or money market.
}
