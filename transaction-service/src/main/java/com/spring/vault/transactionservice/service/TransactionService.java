package com.spring.vault.transactionservice.service;


import com.spring.vault.transactionservice.entity.Payment;
import com.spring.vault.transactionservice.entity.Transaction;
import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.model.RequestWrapper;

import java.util.List;

public interface TransactionService {
    void deposit(AccountRequest accountRequest, long amount);
    void withdraw(AccountRequest accountRequest, long amount);
    void transfer(RequestWrapper requestWrapper, long amount);
    List<Transaction> getTransactions();
    List<Payment> getPayments();
}
