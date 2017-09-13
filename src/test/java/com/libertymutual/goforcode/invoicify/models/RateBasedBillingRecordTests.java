package com.libertymutual.goforcode.invoicify.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

public class RateBasedBillingRecordTests {
	
	@Test
	public void test_all_getters_and_setters() {
		 BeanTester tester = new BeanTester();
	     Configuration configuration = new ConfigurationBuilder()
	    		 .ignoreProperty("createdOn")
	    		 .build();
	      tester.testBean(RateBasedBillingRecord.class, configuration);
	}
	
	@Test
	public void test_getTotal_returns_product() {
		//arrange
		RateBasedBillingRecord rate = new RateBasedBillingRecord();
		rate.setRate(7);
		rate.setQuantity(3);
		
		//act
		double actual = rate.getTotal(); 
		
		//assert
		assertThat(actual).isEqualTo(7*3); 
	}

}
