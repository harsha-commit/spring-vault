package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	// Custom method to find an admin by username
	Admin findByUsername(String username);
	
	// Custom method to delete an admin by username
	void deleteByUsername(String username);

}