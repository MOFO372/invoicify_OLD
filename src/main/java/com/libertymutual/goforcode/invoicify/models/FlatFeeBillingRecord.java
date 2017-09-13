package com.libertymutual.goforcode.invoicify.models;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class FlatFeeBillingRecord extends BillingRecord {

	@Column
	private double amount;


	@Override
	public double getTotal() {
		return amount; 
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
