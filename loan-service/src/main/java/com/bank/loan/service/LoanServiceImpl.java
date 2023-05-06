package com.bank.loan.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.loan.binding.Loan;
import com.bank.loan.binding.LoanStatement;
import com.bank.loan.binding.Payment;
import com.bank.loan.entity.LoanEntity;
import com.bank.loan.entity.LoanStatementEntity;
import com.bank.loan.entity.LoanType;
import com.bank.loan.repository.LoanRepository;
import com.bank.loan.repository.LoanStatementRecordRepository;
import com.bank.loan.repository.LoanTypeRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanRepository loanRepository;
	@Autowired
	LoanTypeRepository loanTypeRepository;
	@Autowired
	LoanStatementRecordRepository loanStatementRecordRepo;

	// dropList for Loan Type
	@Override
	public Map<Integer, String> dropDownLoanType() {
		Map<Integer, String> loanType = new HashMap<>();
		loanTypeRepository.findAll().forEach(lt -> {
			loanType.put(lt.getLoanTypeId(), lt.getLoanType());
		});
		return loanType;
	}

	// customer apply loan to call LoanApply()
	@Override
	public String loanApply(Loan loanRequest) {
		LoanEntity save = loanRepository.save(LoanEntity.builder().customerId(loanRequest.getCustomerId())
				.loanAmount(loanRequest.getLoanAmount()).loanTypeId(loanRequest.getLoanTypeId())
				.duration(loanRequest.getDuration()).status("NIL").appliedOn(LocalDate.now()).build());
		if (save.getLoanId() > 0) {
			return "Applied Successful, you will be notify once verification will be done";
		}
		return "Failed to applied";
	}

	// Loan Worker only can validate whether Customer approved Loan or not.
	@Override
	public String loanVarification(Integer customerId, Integer loanId) {
		log.info("===============================>{} {}", customerId, loanId);
		LoanEntity loanEntity = loanRepository.findByLoanIdAndCustomerId(loanId, customerId);

		// Buissness Logic
		/*
		 * by customerId we can fetch customer detaail from Customer API through feign
		 * client or RestTemplate, Then we can validate loan based on customer details
		 * and update the status accordingly then call Mail Service to notifiying
		 * customer
		 */
		// temprory logic
		// if customer a/c older then 6 month then

		if (loanEntity.getStatus().equalsIgnoreCase("NIL")) {
			loanEntity.setStatus("approved");

			LoanType loanType = loanTypeRepository.findById(loanEntity.getLoanTypeId()).get();

			Double principal = loanEntity.getLoanAmount();
			Integer time = loanEntity.getDuration();
			Double rate = loanType.getIntrestRate();
			log.info(principal);

			log.info(rate);
			Double amountToBePaid;

			log.info("Intrest=p*r*t {} * {} * {}", principal, rate, time);
			Double interest = principal * rate * time / 12 / 100;
			log.info("Intrest to be {}", interest);
			amountToBePaid = (principal + interest);

			if (loanEntity.getStatus().equalsIgnoreCase("approved")) {
				log.info("inside condition==={}", loanEntity.getStatus());
				loanEntity.setIntrestRate(rate);
				loanEntity.setIntrestAmount(interest);
				loanEntity.setStartDate(LocalDate.now());
				loanEntity.setEndDate(LocalDate.now().plusMonths(time));
				// loanRepository.save(loanEntity);
				log.info("inside LoanEntity{}", loanEntity.toString());
				// loanRepository.save(loanEntity);
				loanRepository.save(loanEntity);
				statementGenerator(loanEntity);
			}
			return "Loan Verified";
		} else {
			return "Loan already Verified";
		}
	}

	public List<LoanStatement> repaymentStmt(Integer custId, Integer loanId) {
		List<LoanStatementEntity> stmt = loanStatementRecordRepo.findByLoanIdAndCustomerId(loanId, custId);

		LoanStatementEntity closeEntity = loanStatementRecordRepo.closeAmount(custId, loanId);
		Double closeAmount = closeEntity.getCloseAmount();

		System.out.println(closeAmount);
		List<LoanStatementEntity> validateDefaulter = stmt.stream().filter(
				e -> e.getPaidDate() == null && e.getNextDueDate() != null && e.getDefaulter().equalsIgnoreCase("N/A"))
				.map(st -> {

					log.info("Enter{}", st);
					LocalDate nextDueDate = st.getNextDueDate();
					log.info("entered {}", nextDueDate);
					Period between = Period.between(LocalDate.now(), nextDueDate);
					int days = between.getDays();
					if (days < 0) {
						st.setDefaulter("defaulter");
						st.setBounce(true);
						st.setBouncePlanty(450.0);
						Double bouncePlanty = st.getBouncePlanty();
						// st.setCloseAmount(closeAmount + bouncePlanty);
						Double monthPaidAmount = st.getMonthPaidAmount();
						st.setMonthPaidAmount(monthPaidAmount + bouncePlanty);
						closeEntity.setCloseAmount(closeAmount + bouncePlanty);
						loanStatementRecordRepo.save(closeEntity);
						/*
						 * String month; if (LocalDate.now().getDayOfMonth() <= 28) { month =
						 * LocalDate.now().getMonth().toString(); } else { month =
						 * LocalDate.now().plusMonths(1).getMonth().toString(); }
						 */
						// updateRepayment(custId,loanId,month);
					}

					return st;
				}).collect(Collectors.toList());
		List<LoanStatementEntity> updateLoanStmt = loanStatementRecordRepo.saveAll(validateDefaulter);
		return updateLoanStmt.stream().map(up -> {
			LoanStatement ls = new LoanStatement();
			BeanUtils.copyProperties(up, ls);
			return ls;
		}).collect(Collectors.toList());

	}

	@Override
	public String monthlyRepayment(Payment payment) {
		LoanStatementEntity loanStmtEntity = loanStatementRecordRepo.findByLoanIdAndCustomerIdAndNextDueDate(
				payment.getLoanId(), payment.getCustomerId(), payment.getDueDate());
		Double monthPaidAmount = loanStmtEntity.getMonthPaidAmount();
		if (monthPaidAmount == payment.getAmount()) {
			loanStmtEntity.setMonthPaidAmount(monthPaidAmount - payment.getAmount());
			loanStmtEntity.setPaidDate(LocalDate.now());
			loanStmtEntity.setThisMonth(LocalDate.now().getMonth().toString());
			loanStatementRecordRepo.save(loanStmtEntity);
		}
		return "Amount Paid";
	}

	@Override
	public String doFullPayment(Payment payment) {

		LoanStatementEntity closeAmountEn = loanStatementRecordRepo.closeAmount(payment.getCustomerId(),
				payment.getLoanId());
		Double closeAmount = closeAmountEn.getCloseAmount();
		Double payAmount = payment.getAmount();
		if (closeAmount == payAmount) {
			Double paidAmt = closeAmount - payAmount;
			closeAmountEn.setCloseAmount(paidAmt);
			closeAmountEn.setPaidDate(LocalDate.now());
			loanStatementRecordRepo.save(closeAmountEn);
			closeLoanAccount(payment.getCustomerId(), payment.getLoanId());

		}
		return "Done";

	}

	@Override
	public List<Loan> viewLoan(Integer custId) {
		List<LoanEntity> loanEntities = loanRepository.findByCustomerId(custId);
		return loanEntities.stream().map(entity -> {
			Loan loan = new Loan();
			BeanUtils.copyProperties(entity, loan);
			return loan;
		}).collect(Collectors.toList());

	}

	// ALL Private method

	private void closeLoanAccount(Integer custId, Integer loanId) {
		List<LoanStatementEntity> clearStatement = loanStatementRecordRepo.clearStatement(custId, loanId);
		loanStatementRecordRepo.deleteAll(clearStatement);
		log.info(" Statement has been reset");

		LoanEntity loan = loanRepository.findByLoanIdAndCustomerId(loanId, custId);
		loan.setStatus("closed");
		loan.setStatusDate(LocalDate.now());
		loanRepository.save(loan);
		log.info("Loan Account has been closed for Customer {} of Loan Id {}", loan.getCustomerId(), loanId);

	}

	private void statementGenerator(LoanEntity loanEntity) {

		List<LoanStatementEntity> loanStmt = new ArrayList<>();

		Integer duration = loanEntity.getDuration();
		Double principal = loanEntity.getLoanAmount();
		Double intrest = loanEntity.getIntrestAmount();
		Double amountToBePaid = principal + intrest;

		for (int i = 0; i <= duration; i++) {
			LoanStatementEntity record = new LoanStatementEntity();

			// Double monthlyAmt = (amountToBePaid / (duration));
			Double interestMonthlyAmt = intrest;
			Month month = LocalDate.now().plusMonths(i).getMonth();
			String nMonth = month.toString();

			int year = LocalDate.now().plusMonths(i).getYear();
			int emi = duration - i;

			LocalDate nextDue = null;
			LocalDate toBePaidDate = LocalDate.of(year, month.getValue(), 28);
			Double monthlyAmt = 0.0;
			if (i > 0) {
				nextDue = toBePaidDate;
				monthlyAmt = (amountToBePaid / (duration));
				record.setEmiLeft(emi + 1);
			}
			record.setCustomerId(loanEntity.getCustomerId());
			record.setLoanId(loanEntity.getLoanId());
			record.setPrincipalAmount(loanEntity.getLoanAmount());
			if (i == 0) {
				record.setCloseAmount(amountToBePaid);
				record.setDefaulter("N/A");

			}
			record.setDefaulter("N/A");
			record.setMonthPaidAmount(monthlyAmt);
			record.setIntrestMonthAmount(interestMonthlyAmt);
			record.setThisMonth(nMonth);
			record.setNextDueDate(nextDue);

			loanStmt.add(record);
		}
		loanStatementRecordRepo.saveAll(loanStmt);
	}

}
