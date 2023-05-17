package com.bank.notificationservice.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.notificationservice.entity.LoanEntity;
import com.bank.notificationservice.entity.LoanStatementEntity;
import com.bank.notificationservice.externalclient.response.CustomerResponse;
import com.bank.notificationservice.externalclient.service.CustomerClient;
import com.bank.notificationservice.repository.LoanRepository;
import com.bank.notificationservice.repository.LoanStatementRecordRepository;
import com.bank.notificationservice.repository.LoanTypeRepository;
import com.bank.notificationservice.utils.EmailUtils;
import com.google.common.collect.Table;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class NotificationServiceImpl implements NotificationService {

	private static final String PDF_PATH = "D:\\pdf-generated\\";

	@Autowired
	LoanRepository loanRepository;
	@Autowired
	LoanStatementRecordRepository stmtRecord;
	@Autowired
	LoanTypeRepository loanTypeRepo;
	@Autowired
	CustomerClient customerClient;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public Map<String, Integer> generateNotices(Integer custId, Integer loanId) throws Exception {

		processTrigger(custId, loanId);

		return null;
	}

	private void processTrigger(Integer custId, Integer loanId) throws Exception {
		LoanEntity loanDtls = loanRepository.findByLoanIdAndCustomerId(loanId, custId);
		List<LoanStatementEntity> stmt = stmtRecord.findByLoanIdAndCustomerId(loanId, custId);
		ResponseEntity<CustomerResponse> customerResponse = customerClient.getCustomerById(custId);
		CustomerResponse body = customerResponse.getBody();
		log.info(body.toString());
		generatePdf(body, loanDtls, stmt);
		sendPdfAsAnAttachment(body, custId);		
	}

	private void sendPdfAsAnAttachment(CustomerResponse customer, Integer custId) {

		String subject = " Loan Statement - BMS";
		String body = readMailBodyContent(customer);
		String email = customer.getEmail();

		boolean send = emailUtils.sendEmail(email, subject, body, new File(PDF_PATH + custId + ".pdf"));
		log.info(PDF_PATH + custId + ".pdf" + customer.getEmail());
		log.info("send mail {}", send);
	}

	private void generatePdf(CustomerResponse customer, LoanEntity loanDtls, List<LoanStatementEntity> stmt)
			throws FileNotFoundException {

		Document document = new Document();

		FileOutputStream fos = new FileOutputStream(new File(PDF_PATH + loanDtls.getCustomerId() + ".pdf"));

		PdfWriter writer = PdfWriter.getInstance(document, fos);

		document.open();

		// setting font family, color
		Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, Color.RED);
		Paragraph para1 = new Paragraph("Loan EMI Details", font);
		// para1.add("=====================================");

		para1.setAlignment(26);
		para1.setIndentationLeft(55);
		para1.setSpacingAfter(8);
		document.add(para1);

		PdfPTable table1 = new PdfPTable(2);

		table1.setHeaderRows(2);
		table1.addCell("Customer");
		table1.addCell(customer.getFirstName() + " " + customer.getLastName());
		table1.addCell("Loan Type");
		table1.addCell(String.valueOf(loanTypeRepo.findById(loanDtls.getLoanTypeId()).get().getLoanType()));
		table1.addCell("Status");
		table1.addCell(loanDtls.getStatus());
		table1.addCell("Principal Amount");
		table1.addCell(String.valueOf(loanDtls.getLoanAmount()));

		table1.addCell("Start Date");
		table1.addCell(String.valueOf(loanDtls.getStartDate()));
		table1.addCell("End Date");
		table1.addCell(String.valueOf(loanDtls.getEndDate()));

		table1.addCell("Close Amount");
		table1.addCell("");

		table1.setFooterRows(0);
		table1.setSpacingAfter(26);
		table1.completeRow();
		table1.getHorizontalAlignment();
		document.add(table1);

		PdfPTable table = new PdfPTable(9);
		table.addCell("EMI No-");
		table.addCell("Month Amount");
		table.addCell("Monthly Intrest");
		table.addCell("Bounce");
		table.addCell("Bounce Planty");
		table.addCell("Defaulter");
		table.addCell("Due Date");
		table.addCell("Paid Date");
		table.addCell("month");
		table.completeRow();

		for (LoanStatementEntity st : stmt) {
			table.addCell(String.valueOf(st.getEmiLeft()));
			table.addCell(String.valueOf(st.getMonthPaidAmount()));
			table.addCell(String.valueOf(st.getIntrestMonthAmount()));
			table.addCell(String.valueOf(st.getBounce()));
			table.addCell(String.valueOf(st.getBouncePlanty()));
			table.addCell(String.valueOf(st.getDefaulter()));
			table.addCell(String.valueOf(st.getNextDueDate()));
			table.addCell(String.valueOf(st.getPaidDate()));
			table.addCell(String.valueOf(st.getThisMonth()));
			table.completeRow();
		}

		document.add(table);
		Paragraph para3 = new Paragraph("Generated On :" + LocalDate.now(), font);
		document.close();
		writer.close();

	}

	private String readMailBodyContent(CustomerResponse customer) {

		String mailBody = null;
		StringBuilder sb = new StringBuilder();
		Path filePath = Paths.get("EMAIL-BODY-TEMPLATE.txt");

		try (Stream<String> stream = Files.lines(filePath)) {

			stream.forEach(line -> sb.append(line));

			mailBody = sb.toString();
			mailBody = mailBody.replace("{FNAME}", customer.getFirstName() + " " + customer.getLastName());

		} catch (Exception e) {
			log.error("Exception ::" + e.getMessage(), e);
		}

		return mailBody;
	}

}
