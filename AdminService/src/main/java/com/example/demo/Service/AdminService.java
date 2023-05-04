package com.example.demo.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    public String getCustomer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        
        return restTemplate.exchange(
        		 "http://localhost:8084/register", HttpMethod.GET, entity, String.class).getBody();
    }
    
    public String getCustomerDetails() {
    	HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        
        return restTemplate.exchange(
        		"http://localhost:8081/customer/{id}", HttpMethod.GET, entity, String.class).getBody();
    }
    
    public String deleteCustomer() {
    	 HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         HttpEntity <String> entity = new HttpEntity<String>(headers);
         
         return restTemplate.exchange(
        		 "http://localhost:8081/customer/{id}",  HttpMethod.DELETE, entity, String.class).getBody();
    }
}
 
        		 
    

     

   
