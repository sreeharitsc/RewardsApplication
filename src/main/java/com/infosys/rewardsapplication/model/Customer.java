/**
 * 
 */
package com.infosys.rewardsapplication.model;

import java.util.List;

/**
 * @author sreehari
 * class represents customer
 */
public class Customer {

	private Long id;
	private String name;
	private List<Transaction> transactions;
	public Customer(Long id, String name, List<Transaction> transactions2) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.transactions = transactions2;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
