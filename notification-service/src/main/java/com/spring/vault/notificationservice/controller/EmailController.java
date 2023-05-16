package com.spring.vault.notificationservice.controller;

import com.spring.vault.notificationservice.model.EmailRequest;
import com.spring.vault.notificationservice.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/notification")
public class EmailController {
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendEmail")
    public ResponseEntity<HttpStatus> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailSenderService.sendSimpleEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        }
        catch (MailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
