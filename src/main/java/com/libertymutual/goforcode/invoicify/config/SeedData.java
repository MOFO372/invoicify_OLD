package com.libertymutual.goforcode.invoicify.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.libertymutual.goforcode.invoicify.models.User;
import com.libertymutual.goforcode.invoicify.repositories.UserRepository;

@Configuration
@Profile("development")
public class SeedData {
	
	public SeedData(UserRepository usersRepo, PasswordEncoder encoder) {
		usersRepo.save(new User("rachel", encoder.encode("password"), "ADMIN"));
		usersRepo.save(new User("admin", encoder.encode("admin"), "ADMIN"));
		usersRepo.save(new User("clerk", encoder.encode("clerk"), "CLERK"));
		usersRepo.save(new User("accountant", encoder.encode("accountant"), "ACCOUNTANT"));
	}
	
}
