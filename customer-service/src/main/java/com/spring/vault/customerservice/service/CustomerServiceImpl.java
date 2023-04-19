package com.spring.vault.customerservice.service;

import com.spring.vault.customerservice.entity.Customer;
import com.spring.vault.customerservice.model.CustomerRequest;
import com.spring.vault.customerservice.model.CustomerResponse;
import com.spring.vault.customerservice.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    // Exceptions to be handled
    @Override
    public CustomerResponse getCustomerById(long id) {
        log.info("Finding a Customer with ID: {}", id);
        Customer customer = customerRepository.findById(id).get();
        CustomerResponse customerResponse = CustomerResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
        log.info("Customer Found with data: {}", customer);
        return customerResponse;
    }

    @Override
    public void createCustomer(CustomerRequest customerRequest) {
        log.info("Creating the Customer with given data");
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .phoneNumber(customerRequest.getPhoneNumber())
                .address(customerRequest.getAddress())
                .build();
        log.info("Customer {} is CREATED", customer);
        customerRepository.save(customer);
    }
}
