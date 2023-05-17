package com.bank.notificationservice.externalclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.notificationservice.externalclient.response.CustomerResponse;

@FeignClient(name = "CUSTOMER-SERVICE/customer")
public interface CustomerClient {
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable long id);

}
