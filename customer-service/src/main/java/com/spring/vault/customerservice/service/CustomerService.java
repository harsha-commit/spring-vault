package com.spring.vault.customerservice.service;

import com.spring.vault.customerservice.model.CustomerRequest;
import com.spring.vault.customerservice.model.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse getCustomerById(long id);
    List<CustomerResponse> getAllCustomers();
    long createCustomer(CustomerRequest customerRequest);
    void deleteCustomerById(long id);
    void updateCustomer(CustomerRequest customerRequest);
}
