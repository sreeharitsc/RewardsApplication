/**
 * 
 */
package com.infosys.rewardsapplication.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author sreehari
 * class represents customer transactions
 */
@Entity
public class Transaction {

	@Id
	private String transactionId;
	
	private LocalDate date;
	private Double amount;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	
	/**
	 * @param transactionId
	 * @param date
	 * @param amount
	 * @param customer
	 */
	public Transaction(String transactionId, LocalDate date, Double amount, Customer customer) {
		super();
		this.transactionId = transactionId;
		this.date = date;
		this.amount = amount;
		this.customer = customer;
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
	
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "{date:"+date+", amount:"+amount+"}";
	}
}
