/**
 * 
 */
package com.infosys.rewardsapplication.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author sreehari
 * class represents customer
 */
@Entity
public class Customer {

	@Id
	private String id;
	
	private String name;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL )
	private List<Transaction> transactions;
	
	public Customer(String id, String name, List<Transaction> transactions2) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.transactions = transactions2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
