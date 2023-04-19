package com.spring.vault.transactionservice.service;

import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.external.response.AccountResponse;
import com.spring.vault.transactionservice.model.TransactionRequest;
import com.spring.vault.transactionservice.model.TransactionResponse;
import com.spring.vault.transactionservice.entity.Transaction;
import com.spring.vault.transactionservice.model.TransactionType;
import com.spring.vault.transactionservice.repository.TransactionRepository;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Exceptions to be handled
    @Override
    public TransactionResponse getTransactionById(long id) {
        Transaction transaction = transactionRepository.findById(id).get();
        TransactionResponse transactionResponse = TransactionResponse.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .transactionType(TransactionType.valueOf(transaction.getTransactionType()))
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .build();
        return transactionResponse;
    }

    @Override
    public long deposit(TransactionRequest transactionRequest) {
        Transaction transaction = Transaction.builder()
                .accountId(transactionRequest.getAccountId())
                .amount(transactionRequest.getAmount())
                .transactionType("DEPOSIT")
                .date(Instant.now())
                .build();

        // add amount in the account

        // the object to be sent
        AccountRequest accountRequest = AccountRequest.builder()
                .amount(transactionRequest.getAmount())
                .build();

        // add data in reporting service
        restTemplate.postForObject("http://ACCOUNT-SERVICE/account/add/" + transactionRequest.getAccountId(),
                accountRequest,
                AccountRequest.class
                );

        transactionRepository.save(transaction);
        return transaction.getId();
    }

    @Override
    public long withdrawal(TransactionRequest transactionRequest) {
        Transaction transaction = Transaction.builder()
                .accountId(transactionRequest.getAccountId())
                .amount(transactionRequest.getAmount())
                .transactionType("WITHDRAWAL")
                .date(Instant.now())
                .build();

        // subtract amount in the account

        // add data in reporting service

        transactionRepository.save(transaction);
        return transaction.getId();
    }
}
