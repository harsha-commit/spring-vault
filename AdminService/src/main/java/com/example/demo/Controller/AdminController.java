package com.example.demo.Controller;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	 @Autowired
	    private AdminService adminService;
	 @Autowired
	    private RestTemplate restTemplate;

	    // Fetch customer data
	    @GetMapping("/customers")
	    public ResponseEntity<String> getCustomers() {
	        String customerData = adminService.getCustomer();
	        return new ResponseEntity<>(customerData, HttpStatus.OK);
	    }
	    
	    // Fetch customer details
	    @GetMapping("/customer/{id}")
	    public String getCustomerDetails(@PathVariable("id") String customerId) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity<String> entity = new HttpEntity<>(headers);
	        
	        // Make REST API call using RestTemplate
	        ResponseEntity<String> response = restTemplate.exchange(
	                "http://localhost:8081/customer/{id}", HttpMethod.GET, entity, String.class, customerId);
	        
	        return response.getBody();
	    }
	    
	    @DeleteMapping("/customer/{id}")
	    public String deleteCustomer(@PathVariable("id") String customerId) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity<String> entity = new HttpEntity<>(headers);
	        
	        // Make REST API call using RestTemplate to delete the customer
	        restTemplate.exchange(
	                "http://localhost:8081/customer/{id}", HttpMethod.DELETE, entity, Void.class, customerId);
	        
	        return "Customer deleted successfully";
	    }


}
