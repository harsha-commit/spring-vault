package com.bank.loan.externalclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.loan.externalclient.response.AccountResponse;

@FeignClient(name = "ACCOUNT-SERVICE/account")
public interface AccountClient {

	 @GetMapping("/{id}")
	    public ResponseEntity<AccountResponse> getAccountById(@PathVariable long id);
}
