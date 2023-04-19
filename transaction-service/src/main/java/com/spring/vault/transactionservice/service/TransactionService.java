package com.spring.vault.transactionservice.service;

import com.spring.vault.transactionservice.model.TransactionRequest;
import com.spring.vault.transactionservice.model.TransactionResponse;

public interface TransactionService {
    TransactionResponse getTransactionById(long id);
    long deposit(TransactionRequest transactionRequest);
    long withdrawal(TransactionRequest transactionRequest);
}
