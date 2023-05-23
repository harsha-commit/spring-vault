package com.spring.vault.transactionservice.controller;

import com.spring.vault.transactionservice.entity.Payment;
import com.spring.vault.transactionservice.entity.Transaction;
import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.model.RequestWrapper;
import com.spring.vault.transactionservice.model.TransferObject;
import com.spring.vault.transactionservice.service.TransactionService;
import jakarta.ws.rs.Path;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/{id}")
    // based on customer_id and account_name
    public ResponseEntity<List<Transaction>> getTransactionsByCustomerIdAndAccountName(@PathVariable long id, @RequestParam String name){
        return new ResponseEntity<>(transactionService.getTransactionsByCustomerIdAndAccountName(id, name), HttpStatus.OK);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<List<Payment>> getPaymentsBySourceId(@PathVariable long id){
        return new ResponseEntity<>(transactionService.getPaymentsBySourceId(id), HttpStatus.OK);
    }

    // Deposit
    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<HttpStatus> deposit(@RequestBody AccountRequest accountRequest, @PathVariable long accountId,@RequestParam long amount){
        transactionService.deposit(accountRequest, accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Withdraw
    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<HttpStatus> withdraw(@RequestBody AccountRequest accountRequest, @PathVariable long accountId,@RequestParam long amount){
        transactionService.withdraw(accountRequest, accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transfer/{amount}")
    public ResponseEntity<Boolean> transfer(@RequestBody TransferObject transferObject, @PathVariable long amount){
        boolean result = transactionService.transfer(transferObject, amount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
