package com.libertymutual.goforcode.invoicify.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RateBasedBillingRecord extends BillingRecord{
	
	@Column
	private double rate; 
	
	@Column
	private double quantity; 
	
	
	@Override
	public double getTotal() {
		double total = rate * quantity; 
		return total;
	}



	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
