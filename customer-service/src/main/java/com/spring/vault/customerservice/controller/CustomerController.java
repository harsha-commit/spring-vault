package com.spring.vault.customerservice.controller;

import com.spring.vault.customerservice.model.CustomerRequest;
import com.spring.vault.customerservice.model.CustomerResponse;
import com.spring.vault.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerRequest customerRequest){
        customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
