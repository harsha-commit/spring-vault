package com.spring.vault.transactionservice.service;

import com.spring.vault.transactionservice.entity.Payment;
import com.spring.vault.transactionservice.entity.Transaction;
import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.model.RequestWrapper;
import com.spring.vault.transactionservice.repository.PaymentRepository;
import com.spring.vault.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void deposit(AccountRequest accountRequest, long amount) {
        // get the balance
        accountRequest.setBalance(accountRequest.getBalance() + amount);

        // update the account
        restTemplate.put("http://ACCOUNT-SERVICE/account", accountRequest);

        Transaction transaction = Transaction.builder()
                .accountName(accountRequest.getName())
                .amount(amount)
                .transactionType("DEPOSIT")
                .status("SUCCESS")
                .build();

        // store the transaction
        transactionRepository.save(transaction);
    }

    @Override
    public void withdraw(AccountRequest accountRequest, long amount) {

        // get the balance
        accountRequest.setBalance(accountRequest.getBalance() - amount);

        // update the account
        restTemplate.put("http://ACCOUNT-SERVICE/account", accountRequest);

        Transaction transaction = Transaction.builder()
                .accountName(accountRequest.getName())
                .amount(amount)
                .transactionType("WITHDRAW")
                .status("SUCCESS")
                .build();

        // store the transaction
        transactionRepository.save(transaction);
    }

    @Override
    public void transfer(RequestWrapper requestWrapper, long amount) {
        AccountRequest accountRequest1 = requestWrapper.getAccountRequest1();
        AccountRequest accountRequest2 = requestWrapper.getAccountRequest2();

        // Update the Balances
        accountRequest1.setBalance(accountRequest1.getBalance() - amount);
        accountRequest2.setBalance(accountRequest2.getBalance() + amount);

        restTemplate.put("http://ACCOUNT-SERVICE/account", accountRequest1);
        restTemplate.put("http://ACCOUNT-SERVICE/account", accountRequest2);

        // store the payment
        Payment payment1 = Payment.builder()
                .accountName(accountRequest1.getName())
                .amount(amount)
                .status("SUCCESS")
                .reference("DEBITED")
                .date(Instant.now())
                .build();

        paymentRepository.save(payment1);

        Payment payment2 = Payment.builder()
                .accountName(accountRequest2.getName())
                .amount(amount)
                .status("SUCCESS")
                .reference("CREDITED")
                .date(Instant.now())
                .build();

        paymentRepository.save(payment2);
    }
}


