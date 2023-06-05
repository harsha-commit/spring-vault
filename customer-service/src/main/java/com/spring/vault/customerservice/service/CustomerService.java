package com.spring.vault.customerservice.service;

import com.spring.vault.customerservice.entity.Customer;
import com.spring.vault.customerservice.model.CustomerRequest;
import com.spring.vault.customerservice.model.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse getCustomerById(long id);
    List<Customer> getAllCustomers();
    long createCustomer(CustomerRequest customerRequest);
    void deleteCustomerById(long id);
    void updateCustomer(CustomerRequest customerRequest);
    CustomerResponse customerExists(long customerId, String password);
}
