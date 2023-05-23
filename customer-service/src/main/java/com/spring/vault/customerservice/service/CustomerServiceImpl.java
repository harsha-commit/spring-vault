package com.spring.vault.customerservice.service;

import com.spring.vault.customerservice.entity.Customer;
import com.spring.vault.customerservice.exception.CustomerNotFoundException;
import com.spring.vault.customerservice.external.client.NotificationService;
import com.spring.vault.customerservice.model.CustomerRequest;
import com.spring.vault.customerservice.model.CustomerResponse;
import com.spring.vault.customerservice.model.EmailRequest;
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
    @Autowired
    private NotificationService notificationService;

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

    // CustomerRequest =>
    @Override
    public long createCustomer(CustomerRequest customerRequest) {

        // Check if email already exists
        if (customerRepository.existsByEmail(customerRequest.getEmail())) {
            return -1;
        }

        // Check if PAN number already exists
        if (customerRepository.existsByPanNumber(customerRequest.getPanNumber())) {
            return -2;
        }

        // Check if Aadhar number already exists
        if (customerRepository.existsByAadharNumber(customerRequest.getAadharNumber())) {
            return -3;
        }

        // Check if phone number already exists
        if (customerRepository.existsByPhoneNumber(customerRequest.getPhoneNumber())) {
            return -4;
        }

        log.info("Creating the Customer with given data");
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .panNumber(customerRequest.getPanNumber())
                .aadharNumber(customerRequest.getAadharNumber())
                .email(customerRequest.getEmail())
                .phoneNumber(customerRequest.getPhoneNumber())
                .address(customerRequest.getAddress())
                .password(customerRequest.getPassword())
                .build();

        customerRepository.save(customer);
        log.info("Customer {} is CREATED", customer);

        String to = customerRequest.getEmail();
        String text =  " Congratulations 🎉. \n Dear " + customerRequest.getFirstName() + ",\n\n Thank you for creating an account with us! Your customer ID is " + customer.getId();
        notificationService.sendEmail(new EmailRequest(to,"Spring Vault User Account CREATED 🔥", text));

        return customer.getId();
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

    @Override
    public CustomerResponse customerExists(long customerId, String password) {

        Customer customer = customerRepository.findByIdAndPassword(customerId, password).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found", "NOT_FOUND"));
        CustomerResponse customerResponse = CustomerResponse.builder()
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .panNumber(customer.getPanNumber())
                    .aadharNumber(customer.getAadharNumber())
                    .email(customer.getEmail())
                    .phoneNumber(customer.getPhoneNumber())
                    .address(customer.getAddress())
                    .build();

        return customerResponse;
    }

}
