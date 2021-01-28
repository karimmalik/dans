package com.karim.dans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.karim.dans.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM user where username = ?1")
	public User findByUsername(String username);
	
}
