package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.RegistrationLogin;

public interface RegistrationLoginRepository extends JpaRepository<RegistrationLogin, Long> {
	
	 // Custom method to find a user by email
    RegistrationLogin findByEmail(String email);

	RegistrationLogin findByEmailAndPassword(String email, String password);


}
