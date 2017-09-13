package com.libertymutual.goforcode.invoicify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.invoicify.models.User;

@Repository
//using Long since the value of the id is null and needs to be created (capital Long means it's nullable)
public interface UserRepository extends JpaRepository<User, Long> {

	//creates SQL that will find one user name since we are extending JPA repository 
	User findByUsername(String username); 
	
}
