package com.bank.notificationservice.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String to, String subject, String body,File file) {
		boolean isSent = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			helper.addAttachment(file.getName(),file);
			
			mailSender.send(mimeMessage);
			isSent = true;
		} catch (Exception e) {
			log.error("Exception ::" + e.getMessage(),e);
		}
		return isSent;
	}
	
}
