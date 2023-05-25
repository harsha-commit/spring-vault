package com.spring.vault.transactionservice.service;


import com.spring.vault.transactionservice.entity.Payment;
import com.spring.vault.transactionservice.entity.Transaction;
import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.model.RequestWrapper;
import com.spring.vault.transactionservice.model.TransferObject;

import java.util.List;

public interface TransactionService {
    void deposit(AccountRequest accountRequest, long accountId, long amount);
    void withdraw(AccountRequest accountRequest, long accountId, long amount);
    boolean transfer(TransferObject transferObject, long amount);
    List<Transaction> getTransactionsByCustomerIdAndAccountName(long id, String name);
    List<Payment> getPaymentsBySourceId(long id);
}
