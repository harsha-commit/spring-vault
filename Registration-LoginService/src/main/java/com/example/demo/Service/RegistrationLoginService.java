package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.RegistrationLogin;
import com.example.demo.Repository.RegistrationLoginRepository;

@Service
public class RegistrationLoginService {
	
	 @Autowired
	    private RegistrationLoginRepository registrationLoginRepository;
	    // Register a new user
	    public RegistrationLogin registerUser(RegistrationLogin user) {
	        // Check if the user already exists
	        if (registrationLoginRepository.findByEmail(user.getEmail()) != null) {
	            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
	        }

	        // Save the user to the database
	        return registrationLoginRepository.save(user);
	    }
	    
	    public RegistrationLogin loginUser(String email, String password) {
	        // Check if the user exists and the password is correct
	        RegistrationLogin user = registrationLoginRepository.findByEmailAndPassword(email, password);
	        if (user == null) {
	            throw new RuntimeException("Invalid email or password");
	        }

	        return user;
	    }
	}