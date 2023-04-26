package com.spring.vault.accountservice.service;

import com.spring.vault.accountservice.model.AccountRequest;
import com.spring.vault.accountservice.model.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse getAccountById(long id);
    List<AccountResponse> getAllAccounts();
    void createAccount(AccountRequest accountRequest);
    void updateAccount(AccountRequest accountRequest);
    void deleteAccountById(long id);
}
