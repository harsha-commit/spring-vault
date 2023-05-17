package com.bank.notificationservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.notificationservice.service.NotificationService;

@RestController
@RequestMapping("statement")
public class LoanNotice {

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/notices/{custId}/{loanId}")
	public Map<String, Integer> generateNotices(@PathVariable Integer custId, @PathVariable Integer loanId)
			throws Exception {
		return notificationService.generateNotices(custId, loanId);
	}
}
