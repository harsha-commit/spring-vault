package com.spring.vault.accountservice.service;

import com.spring.vault.accountservice.model.AccountRequest;
import com.spring.vault.accountservice.model.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse getAccountById(long id);
    List<AccountResponse> getAllAccounts();
    void createAccount(AccountRequest accountRequest);
    void updateAccount(AccountRequest accountRequest, long id);
    void deleteAccountById(long id);
    List<AccountResponse> getAllAccountsById(long id);
    void addAmount(long accountId, long amount);
    void subtractAmount(long accountId, long amount);
    List<String> getAccountNamesById(long id);
}
