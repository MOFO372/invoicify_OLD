package com.libertymutual.goforcode.invoicify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
			.antMatchers("/", "/css/**", "/js/**").permitAll()
			.antMatchers("/invoices/**", "/billing-records/**", "/admin/**").hasRole("ADMIN")
			.antMatchers("/billing-records/**").hasRole("CLERK")
			.antMatchers("/invoices/**").hasRole("ACCOUNTANT")
			.anyRequest().authenticated()
		.and()
		.formLogin();
	}
	
	@Bean
	public UserDetailsService userDetailService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("bitch").password("bitchplease").roles("ADMIN").build());
		manager.createUser(User.withUsername("mofo").password("yourmom").roles("ACCOUNTANT").build()); 
		manager.createUser(User.withUsername("dinorawr").password("rawr").roles("CLERK").build()); 
		
		return manager;
	}
}
