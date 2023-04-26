package com.spring.vault.transactionservice.controller;

import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.model.RequestWrapper;
import com.spring.vault.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    // FOR TRANSACTIONS

    // Deposit
    @PostMapping("/deposit")
    public ResponseEntity<HttpStatus> deposit(@RequestBody AccountRequest accountRequest, @RequestParam long amount){
        transactionService.deposit(accountRequest, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Withdraw
    @PostMapping("/withdraw")
    public ResponseEntity<HttpStatus> withdraw(@RequestBody AccountRequest accountRequest, @RequestParam long amount){
        transactionService.withdraw(accountRequest, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // FOR PAYMENTS
    // source to destination
    @PostMapping("/transfer")
    public ResponseEntity<HttpStatus> transfer(@RequestBody RequestWrapper requestWrapper, @RequestParam long amount){
        transactionService.transfer(requestWrapper, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
