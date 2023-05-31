package com.spring.vault.transactionservice.controller;

import com.spring.vault.transactionservice.entity.Payment;
import com.spring.vault.transactionservice.entity.Transaction;
import com.spring.vault.transactionservice.entity.TransactionMongo;
import com.spring.vault.transactionservice.external.request.AccountRequest;
import com.spring.vault.transactionservice.model.TransferObject;
import com.spring.vault.transactionservice.service.TransactionMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionMongoController {

    @Autowired
    TransactionMongoService transactionMongoService;

    @GetMapping("/v2/{id}")
    // based on customer_id and account_name
    public ResponseEntity<List<Transaction>> getTransactionsByCustomerIdAndAccountName(@PathVariable long id, @RequestParam String name){
        return new ResponseEntity<>(transactionMongoService.findByCustomerIdAndAccountName(id, name), HttpStatus.OK);
    }

    @GetMapping("/payments/v2/{id}")
    public ResponseEntity<List<Payment>> getPaymentsBySourceId(@PathVariable long id){
        return new ResponseEntity<>(transactionMongoService.getPaymentsBySourceId(id), HttpStatus.OK);
    }

    @PostMapping("/deposit/v2/{accountId}")
    public ResponseEntity<HttpStatus> deposit(@RequestBody AccountRequest accountRequest, @PathVariable long accountId, @RequestParam long amount){
        transactionMongoService.deposit(accountRequest, accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Withdraw
    @PostMapping("/withdraw/v2/{accountId}")
    public ResponseEntity<HttpStatus> withdraw(@RequestBody AccountRequest accountRequest, @PathVariable long accountId,@RequestParam long amount){
        transactionMongoService.withdraw(accountRequest, accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transfer/v2/{amount}")
    public ResponseEntity<Boolean> transfer(@RequestBody TransferObject transferObject, @PathVariable long amount){
        boolean result = transactionMongoService.transfer(transferObject, amount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
