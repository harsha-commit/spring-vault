package com.spring.vault.customerservice.service;

import com.spring.vault.customerservice.entity.Customer;
import com.spring.vault.customerservice.model.CustomerRequest;
import com.spring.vault.customerservice.model.CustomerResponse;
import com.spring.vault.customerservice.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    // EXCEPTIONS TO BE HANDLED

    @Override
    public CustomerResponse getCustomerById(long id) {
        log.info("Finding a Customer with ID: {}", id);
        Customer customer = customerRepository.findById(id).get();
        CustomerResponse customerResponse = CustomerResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .panNumber(customer.getPanNumber())
                .aadharNumber(customer.getAadharNumber())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
        log.info("Customer Found with data: {}", customer);
        return customerResponse;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        log.info("Getting all Customers");
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer c: customers){
            // Creating and Adding Customer Responses from Customers
            customerResponses.add(CustomerResponse.builder()
                    .firstName(c.getFirstName())
                    .lastName(c.getLastName())
                    .panNumber(c.getPanNumber())
                    .aadharNumber(c.getAadharNumber())
                    .email(c.getEmail())
                    .phoneNumber(c.getPhoneNumber())
                    .address(c.getAddress())
                    .build());
        }
        return customerResponses;
    }

    @Override
    public void createCustomer(CustomerRequest customerRequest) {
        log.info("Creating the Customer with given data");
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .panNumber(customerRequest.getPanNumber())
                .aadharNumber(customerRequest.getAadharNumber())
                .email(customerRequest.getEmail())
                .phoneNumber(customerRequest.getPhoneNumber())
                .address(customerRequest.getAddress())
                .build();
        customerRepository.save(customer);
        log.info("Customer {} is CREATED", customer);
    }

    @Override
    public void deleteCustomerById(long id) {
        log.info("Deleting a Customer with ID: {}", id);
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomer(CustomerRequest customerRequest) {
        log.info("Updating the Customer with given data");
        Customer customer = customerRepository.findByPanNumber(customerRequest.getPanNumber()).get();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setAddress(customerRequest.getAddress());
        customerRepository.save(customer);
    }

}
