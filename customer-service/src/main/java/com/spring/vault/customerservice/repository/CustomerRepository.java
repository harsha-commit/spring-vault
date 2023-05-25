package com.spring.vault.customerservice.repository;

import com.spring.vault.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPanNumber(String panNumber);
    Optional<Customer> findByIdAndPassword(Long customerId, String password);
    boolean existsByEmail(String email);
    boolean existsByPanNumber(String panNumber);
    boolean existsByAadharNumber(String aadharNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
