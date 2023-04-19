package com.spring.vault.accountservice.service;

import com.spring.vault.accountservice.entity.Account;
import com.spring.vault.accountservice.model.AccountRequest;
import com.spring.vault.accountservice.model.AccountResponse;
import com.spring.vault.accountservice.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public long addAmount(long id, AccountRequest accountRequest) {
        Account account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance() + accountRequest.getAmount());
        accountRepository.save(account);
        return account.getId();
    }

    @Override
    public AccountResponse getAccountById(long id) {
        Account account = accountRepository.findById(id).get();
        AccountResponse accountResponse = AccountResponse.builder()
                .id(account.getId())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .customerId(account.getCustomerId())
                .build();

        return accountResponse;
    }
}
