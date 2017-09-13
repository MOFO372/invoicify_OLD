package com.libertymutual.goforcode.invoicify.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserRoleTests {
	
	@Test
	public void test_all_getters_and_setters() {
		new BeanTester().testBean(UserRole.class);
	}
	
	@Test
	public void test_constructor() {
		//arrange/act
		User user = new User("curtis", "potato", "orange");
		UserRole userrole = new UserRole("ALL HAIL", user);
		
		//assert
		assertThat(userrole.getUser()).isSameAs(user);
		assertThat(userrole.getName()).isEqualTo("ALL HAIL"); 
	}
	

}
