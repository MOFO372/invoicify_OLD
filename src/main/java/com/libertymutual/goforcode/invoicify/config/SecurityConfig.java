package com.libertymutual.goforcode.invoicify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.libertymutual.goforcode.invoicify.services.InvoicifyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private InvoicifyUserDetailsService userDetailsService;
	
	public SecurityConfig(InvoicifyUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http
		.authorizeRequests()
			.antMatchers("/", "/css/**", "/js/**", "/signup").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/billing-records/**").hasAnyRole("CLERK", "ADMIN")
			.antMatchers("/invoices/**").hasAnyRole("ACCOUNTANT", "ADMIN")
			.anyRequest().authenticated()
		.and()
		.formLogin();
	}
	
	
	@Bean //means it is available for injectionable for use elsewhere in the code (like a parameter); all annotations are injectable
	public PasswordEncoder passwordEncoded() {
		return new BCryptPasswordEncoder();
	}
	
	
	//this is an interface that loads core user data
	@Override
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}
}
