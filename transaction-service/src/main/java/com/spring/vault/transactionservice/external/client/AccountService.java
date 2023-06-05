package com.spring.vault.transactionservice.external.client;

import com.spring.vault.transactionservice.external.response.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ACCOUNT-SERVICE", url="http://account-service-svc/account")
public interface AccountService {
    @GetMapping("/add/{accountId}")
    public ResponseEntity<HttpStatus> addAmount(@PathVariable long accountId, @RequestParam long amount);

    @GetMapping("/subtract/{accountId}")
    public ResponseEntity<HttpStatus> subtractAmount(@PathVariable long accountId, @RequestParam long amount);

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable long id);

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> doesAccountExist(@PathVariable long id);
}
