package com.spring.vault.transactionservice.service;


import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.model.RequestWrapper;

public interface TransactionService {
    void deposit(AccountRequest accountRequest, long amount);
    void withdraw(AccountRequest accountRequest, long amount);
    void transfer(RequestWrapper requestWrapper, long amount);
}
