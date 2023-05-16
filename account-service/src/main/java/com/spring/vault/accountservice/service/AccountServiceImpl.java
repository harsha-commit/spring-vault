package com.spring.vault.accountservice.service;

import com.spring.vault.accountservice.entity.Account;
import com.spring.vault.accountservice.model.AccountRequest;
import com.spring.vault.accountservice.model.AccountResponse;
import com.spring.vault.accountservice.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public AccountResponse getAccountById(long id) {
        log.info("Finding an Account with ID: {}", id);
        Account account = accountRepository.findById(id).get();
        AccountResponse accountResponse = AccountResponse.builder()
                .id(account.getId())
                .accountType(account.getAccountType())
                .name(account.getName())
                .balance(account.getBalance())
                .customerId(account.getCustomerId())
                .build();
        return accountResponse;
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        log.info("Getting all Accounts");
        List<Account> accounts = accountRepository.findAll();
        List<AccountResponse> accountResponses = new ArrayList<>();
        for(Account account: accounts){
            accountResponses.add(AccountResponse.builder()
                    .id(account.getId())
                    .accountType(account.getAccountType())
                    .name(account.getName())
                    .balance(account.getBalance())
                    .customerId(account.getCustomerId())
                    .build());
        }
        return accountResponses;
    }

    @Override
    public void createAccount(AccountRequest accountRequest) {
        Account account = Account.builder()
                .balance(accountRequest.getBalance())
                .name(accountRequest.getName())
                .customerId(accountRequest.getCustomerId())
                .accountType(accountRequest.getAccountType())
                .build();
        accountRepository.save(account);
        log.info("Account with ID: {} CREATED", account.getId());
    }

    @Override
    public void updateAccount(AccountRequest accountRequest, long id) {
        Account account = accountRepository.findById(id).get();
        account.setBalance(accountRequest.getBalance());
        account.setAccountType(accountRequest.getAccountType());
        accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountResponse> getAllAccountsById(long id) {
        List<Account> accounts = accountRepository.findAllByCustomerId(id);
        List<AccountResponse> accountResponses = new ArrayList<>();
        for(Account account: accounts){
            accountResponses.add(AccountResponse.builder()
                    .id(account.getId())
                    .accountType(account.getAccountType())
                    .name(account.getName())
                    .balance(account.getBalance())
                    .customerId(account.getCustomerId())
                    .build());
        }
        return accountResponses;
    }

    @Override
    public void addAmount(long accountId, long amount) {
        Account account = accountRepository.findById(accountId).get();
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void subtractAmount(long accountId, long amount) {
        Account account = accountRepository.findById(accountId).get();
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    @Override
    public List<String> getAccountNamesById(long id) {
        return null;
    }

}
