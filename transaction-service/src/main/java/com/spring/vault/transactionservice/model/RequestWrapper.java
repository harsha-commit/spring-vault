package com.spring.vault.transactionservice.model;

import com.spring.vault.transactionservice.external.request.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestWrapper {
    AccountRequest accountRequest1;
    AccountRequest accountRequest2;
}
