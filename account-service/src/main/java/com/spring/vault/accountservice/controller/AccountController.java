package com.spring.vault.accountservice.controller;

import com.spring.vault.accountservice.model.AccountRequest;
import com.spring.vault.accountservice.model.AccountResponse;
import com.spring.vault.accountservice.service.AccountService;
import org.apache.hc.client5.http.ssl.HttpsSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {
    /*
        GET /accounts - Retrieves a list of all customer accounts in the database.
        GET /accounts/{id} - Retrieves a specific customer account based on its ID.
        POST /accounts - Creates a new customer account in the database.
        PUT /account - Updates an existing customer account in the database.
        DELETE /accounts/{id} - Deletes a customer account from the database.
     */
    @Autowired
    private AccountService accountService;

    @GetMapping("/names/{id}")
    public  ResponseEntity<List<String>> getAccountNamesById(@PathVariable long id){
        List<String> accountNames = accountService.getAccountNamesById(id);
        return new ResponseEntity<>(accountNames, HttpStatus.OK);
    }

    // Get Account By ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable long id){
        AccountResponse accountResponse = accountService.getAccountById(id);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    // Get All Accounts
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(){
        List<AccountResponse> accountResponses = accountService.getAllAccounts();
        return new ResponseEntity<>(accountResponses, HttpStatus.OK);
    }

    // Get All Accounts By Customer ID
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<AccountResponse>> getAllAccountsById(@PathVariable long id){
        List<AccountResponse> accountResponses = accountService.getAllAccountsById(id);
        return new ResponseEntity<>(accountResponses, HttpStatus.OK);
    }

    // Create an Account
    @PostMapping
    // First Check if Customer with given ID exists
    public ResponseEntity<HttpStatus> createAccount(@RequestBody AccountRequest accountRequest){
        accountService.createAccount(accountRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Update an Account
    @PutMapping("/{accountId}")
    public ResponseEntity<HttpStatus> updateAccount(@RequestBody AccountRequest accountRequest, @PathVariable long accountId){
        accountService.updateAccount(accountRequest, accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Add amount
    @GetMapping("/add/{accountId}")
    public ResponseEntity<HttpStatus> addAmount(@PathVariable long accountId, @RequestParam long amount){
        accountService.addAmount(accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Subtract amount
    @GetMapping("/subtract/{accountId}")
    public ResponseEntity<HttpStatus> subtractAmount(@PathVariable long accountId, @RequestParam long amount){
        accountService.subtractAmount(accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete an Account
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccountById(@PathVariable long id){
        accountService.deleteAccountById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
