package com.bank.notificationservice.service;

import java.util.Map;

public interface NotificationService {

	public Map<String, Integer> generateNotices(Integer custommerId, Integer loanId) throws Exception;
}
