package com.bank.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.notificationservice.entity.LoanType;

public interface LoanTypeRepository extends JpaRepository<LoanType, Integer>{

}
