package com.spring.vault.customerservice.external.client;

import com.spring.vault.customerservice.model.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "notification", url="http://notification-service-svc/notification")
public interface NotificationService {
    @RequestMapping(method = RequestMethod.POST, value = "/sendEmail")
    ResponseEntity<HttpStatus> sendEmail(@RequestBody EmailRequest emailRequest);
}

