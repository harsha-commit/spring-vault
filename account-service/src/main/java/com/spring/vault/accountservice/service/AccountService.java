package com.spring.vault.accountservice.service;

import com.spring.vault.accountservice.model.AccountRequest;
import com.spring.vault.accountservice.model.AccountResponse;

public interface AccountService {
    long addAmount(long id, AccountRequest accountRequest);
    AccountResponse getAccountById(long id);
    //long addAmount(AccountRequest accountRequest);
}
