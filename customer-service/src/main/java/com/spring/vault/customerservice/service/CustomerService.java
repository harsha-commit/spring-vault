package com.spring.vault.customerservice.service;

import com.spring.vault.customerservice.model.CustomerRequest;
import com.spring.vault.customerservice.model.CustomerResponse;

public interface CustomerService {
    CustomerResponse getCustomerById(long id);

    void createCustomer(CustomerRequest customerRequest);
}
