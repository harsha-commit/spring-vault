package com.spring.vault.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
