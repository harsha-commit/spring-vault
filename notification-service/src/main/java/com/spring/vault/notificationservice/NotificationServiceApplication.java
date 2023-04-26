package com.spring.vault.notificationservice;

import com.spring.vault.notificationservice.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NotificationServiceApplication {

	@Autowired
	private NotificationService notificationService;
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() throws MessagingException {
		notificationService.sendNotification("harshavardhan.bashavathini@gmail.com", "Welcome to Spring Vault Banking Services","Welcome Mr. Harsha Vardhan. Your Account is Ready. All Set to Go !!");
	}

}
