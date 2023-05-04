package com.bank.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.loan.entity.LoanType;

public interface LoanTypeRepository extends JpaRepository<LoanType, Integer>{

}
