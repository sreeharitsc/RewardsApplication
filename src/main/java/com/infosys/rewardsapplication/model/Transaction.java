/**
 * 
 */
package com.infosys.rewardsapplication.model;

import java.time.LocalDate;

/**
 * @author sreehari
 * class represents customer transactions
 */
public class Transaction {

	private LocalDate date;
	private Double amount;
	public Transaction(LocalDate of, double d) {
		// TODO Auto-generated constructor stub
		this.date = of;
		this.amount = d;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "{date:"+date+", amount:"+amount+"}";
	}
}
