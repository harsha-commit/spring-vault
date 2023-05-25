package com.spring.vault.transactionservice.service;

import com.spring.vault.transactionservice.entity.Payment;
import com.spring.vault.transactionservice.entity.Transaction;
import com.spring.vault.transactionservice.external.client.AccountService;
import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.model.TransferObject;
import com.spring.vault.transactionservice.repository.PaymentRepository;
import com.spring.vault.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public void deposit(AccountRequest accountRequest, long accountId, long amount) {
        // update the account
        // restTemplate.getForObject("http://ACCOUNT-SERVICE/account/add/" + accountId + "?amount=" + amount, Void.class);
        accountService.addAmount(accountId, amount);

        Transaction transaction = Transaction.builder()
                .accountName(accountRequest.getName())
                .customerId(accountRequest.getCustomerId())
                .amount(amount)
                .transactionType("DEPOSIT")
                .status("SUCCESS")
                .reasonCode("TRANSACTION SUCCESSFUL")
                .createdAt(Instant.now())
                .build();

        // store the transaction
        transactionRepository.save(transaction);
    }

    @Override
    public void withdraw(AccountRequest accountRequest, long accountId, long amount) {
        Transaction transaction = null;
        // Balance is lower than Amount Requested
        if(accountRequest.getBalance() < amount){
            transaction = Transaction.builder()
                    .accountName(accountRequest.getName())
                    .customerId(accountRequest.getCustomerId())
                    .amount(amount)
                    .transactionType("WITHDRAW")
                    .status("FAILURE")
                    .reasonCode("INSUFFICIENT FUNDS")
                    .createdAt(Instant.now())
                    .build();
        }
        else{
            //restTemplate.getForObject("http://ACCOUNT-SERVICE/account/subtract/" + accountId + "?amount=" + amount, Void.class);
            accountService.subtractAmount(accountId,amount);
            transaction = Transaction.builder()
                    .accountName(accountRequest.getName())
                    .customerId(accountRequest.getCustomerId())
                    .amount(amount)
                    .transactionType("WITHDRAW")
                    .status("SUCCESS")
                    .reasonCode("TRANSACTION SUCCESSFUL")
                    .createdAt(Instant.now())
                    .build();
        }
        transactionRepository.save(transaction);
    }

    @Override
    public boolean transfer(TransferObject transferObject, long amount) {

        // Source ID and Destination IDs are Account IDs

        // 1. Check if balance is sufficient
        // 2. If Account doesn't exist
        boolean accountExists = accountService.doesAccountExist(transferObject.getDestinationId()).getBody();

        if(!accountExists){
            paymentRepository.save(Payment.builder()
                    .sourceId(transferObject.getSourceId())
                    .accountId(transferObject.getDestinationId())
                    .accountName(transferObject.getDestinationAccountName())
                    .amount(amount)
                    .reference("NO TRANSFER")
                    .status("FAILURE")
                    .reasonCode("ACCOUNT ID DOES NOT EXIST")
                    .createdAt(Instant.now())
                    .build());

            return false;
        }

        String receiverAccountName = accountService.getAccountById(transferObject.getDestinationId()).getBody().getName();
        if(!receiverAccountName.equals(transferObject.getDestinationAccountName())){
            paymentRepository.save(Payment.builder()
                    .sourceId(transferObject.getSourceId())
                    .accountId(transferObject.getDestinationId())
                    .accountName(transferObject.getDestinationAccountName())
                    .amount(amount)
                    .reference("NO TRANSFER")
                    .status("FAILURE")
                    .reasonCode("ACCOUNT NAME DOES NOT EXIST")
                    .createdAt(Instant.now())
                    .build());

            return false;
        }

        // SAFE ZONE

        // restTemplate.getForObject("http://ACCOUNT-SERVICE/account/subtract/" + transferObject.getSourceId() + "?amount=" + amount, Void.class);
        accountService.subtractAmount(transferObject.getSourceId(), amount);

        // restTemplate.getForObject("http://ACCOUNT-SERVICE/account/add/" + transferObject.getDestinationId() + "?amount=" + amount, Void.class);
        accountService.addAmount(transferObject.getDestinationId(), amount);

        // store the payment
        Payment payment1 = Payment.builder()
                .sourceId(transferObject.getSourceId())
                .accountId(transferObject.getDestinationId())
                .accountName(transferObject.getDestinationAccountName())
                .amount(amount)
                .reference("DEBITED")
                .status("SUCCESS")
                .reasonCode("TRANSACTION SUCCESSFUL")
                .createdAt(Instant.now())
                .build();

        paymentRepository.save(payment1);

        Payment payment2 = Payment.builder()
                .sourceId(transferObject.getDestinationId())
                .accountId(transferObject.getSourceId())
                .accountName(transferObject.getSourceAccountName())
                .amount(amount)
                .reference("CREDITED")
                .status("SUCCESS")
                .reasonCode("TRANSACTION SUCCESSFUL")
                .createdAt(Instant.now())
                .build();

        paymentRepository.save(payment2);
        return true;
    }

    @Override
    public List<Transaction> getTransactionsByCustomerIdAndAccountName(long id, String name) {
        return transactionRepository.findByCustomerIdAndAccountName(id, name);
    }

    @Override
    public List<Payment> getPaymentsBySourceId(long id) {
        return paymentRepository.findBySourceId(id);
    }

}


