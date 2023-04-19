package com.spring.vault.transactionservice.controller;

import com.spring.vault.transactionservice.model.TransactionRequest;
import com.spring.vault.transactionservice.model.TransactionResponse;
import com.spring.vault.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    /*
        GET /transactions - Retrieves a list of all transactions in the database.
        GET /transactions/{id} - Retrieves a specific transaction based on its ID.
        POST /transactions/deposit - Processes a deposit transaction.
        POST /transactions/withdrawal - Processes a withdrawal transaction.
        POST /transactions/transfer - Processes a transfer transaction between two customer accounts.
     */

    @Autowired
    TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable long id){
        TransactionResponse transactionResponse = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transactionResponse, HttpStatus.FOUND);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Long> deposit(@RequestBody TransactionRequest transactionRequest){
        long transactionId = transactionService.deposit(transactionRequest);
        return new ResponseEntity<>(transactionId, HttpStatus.OK);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Long> withdrawal(@RequestBody TransactionRequest transactionRequest){
        long transactionId = transactionService.withdrawal(transactionRequest);
        return new ResponseEntity<>(transactionId, HttpStatus.OK);
    }

}
