package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.RegistrationLogin;
import com.example.demo.Service.RegistrationLoginService;

@RestController
public class RegistrationLoginController {
	
	 @Autowired
	    private RegistrationLoginService registrationLoginService;

	    // Registration endpoint
	    @PostMapping("/register")
	    public ResponseEntity<RegistrationLogin> registerUser(@RequestBody RegistrationLogin user) {
	        RegistrationLogin registeredUser = registrationLoginService.registerUser(user);
	        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<RegistrationLogin> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
	        try {
	            RegistrationLogin user = registrationLoginService.loginUser(email, password);
	            return ResponseEntity.ok(user);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }

}
