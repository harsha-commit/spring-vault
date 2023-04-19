package com.spring.vault.accountservice.controller;

import com.spring.vault.accountservice.model.AccountRequest;
import com.spring.vault.accountservice.model.AccountResponse;
import com.spring.vault.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    /*
        GET /accounts - Retrieves a list of all customer accounts in the database.
        GET /accounts/{id} - Retrieves a specific customer account based on its ID.
        POST /accounts - Creates a new customer account in the database.
        PUT /accounts/{id} - Updates an existing customer account in the database.
        DELETE /accounts/{id} - Deletes a customer account from the database.
     */

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable long id){
        AccountResponse accountResponse = accountService.getAccountById(id);
        return new ResponseEntity<>(accountResponse, HttpStatus.FOUND);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Long> addAmount(@PathVariable long id, @RequestBody AccountRequest accountRequest){
        long temp = accountService.addAmount(id, accountRequest);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

}
