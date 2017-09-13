package com.libertymutual.goforcode.invoicify.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserTests {
	
	@Test
	public void test_all_getters_and_setters() {
		new BeanTester().testBean(User.class);
	}
	
	@Test
	public void test_constructor() {
		//arrange/act
		User user = new User("curtis", "potato", "orange");
		
		//assert
		assertThat(user.getUsername()).isEqualTo("curtis");
		assertThat(user.getPassword()).isEqualTo("potato"); 
		assertThat(user.getRoles()).hasSize(1); 
		assertThat(user.getRoles().get(0).getName()).isEqualTo("orange");      
	}
	
	@Test
	public void test_nonExpired_returns_true() {
		User user = new User(); 
		
		boolean actual = user.isAccountNonExpired(); 
		
		assertThat(actual).isTrue(); 
	}
	
	@Test
	public void test_nonLocked_returns_true() {
		User user = new User(); 
		
		boolean actual = user.isAccountNonLocked(); 
		
		assertThat(actual).isTrue(); 
	}
	
	@Test
	public void test_nonCredential_returns_true() {
		User user = new User(); 
		
		boolean actual = user.isCredentialsNonExpired(); 
		
		assertThat(actual).isTrue(); 
	}
	
	@Test
	public void test_enabled_returns_true() {
		User user = new User(); 
		
		boolean actual = user.isEnabled(); 
		
		assertThat(actual).isTrue(); 
	}
}
