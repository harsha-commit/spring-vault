package com.spring.vault.customerservice.controller;

import com.spring.vault.customerservice.model.CustomerRequest;
import com.spring.vault.customerservice.model.CustomerResponse;
import com.spring.vault.customerservice.service.CustomerService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // Get Customer By ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable long id){
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    // Get All Customers
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();
        return new ResponseEntity<>(customerResponses, HttpStatus.OK);
    }

    // Create A Customer
    @PostMapping
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody CustomerRequest customerRequest){
        customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update A Customer (NOT FOR PAN CARD AND AADHAR CARD)
    @PutMapping
    public ResponseEntity<HttpStatus> updateCustomer(@RequestBody CustomerRequest customerRequest){
        customerService.updateCustomer(customerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete a Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
