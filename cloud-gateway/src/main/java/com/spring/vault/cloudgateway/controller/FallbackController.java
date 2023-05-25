package com.spring.vault.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/accountServiceFallBack")
    public String accountServiceFallBack(){
        return "ACCOUNT SERVICE IS DOWN";
    }

    @GetMapping("/customerServiceFallBack")
    public String customerServiceFallBack(){
        return "CUSTOMER SERVICE IS DOWN";
    }

    @GetMapping("/notificationServiceFallBack")
    public String notificationServiceFallBack(){
        return "NOTIFICATION SERVICE IS DOWN";
    }

    @GetMapping("/transactionServiceFallBack")
    public String transactionServiceFallBack(){
        return "TRANSACTION SERVICE IS DOWN";
    }
}
