/**
 * 
 */
package com.infosys.rewardsapplication.dto;

import java.util.List;
import java.util.Map;

import com.infosys.rewardsapplication.model.Transaction;

/**
 * @author sreehari
 * class used to represent reward response 
 */
public class RewardResponse {

	private String customerId;
	private String customerName;
	private Map<String, Integer> monthlyRewards;
	private int totalRewards;
	private List<Transaction> transactions;
	/**
	 * @param customerId
	 * @param customerName
	 * @param monthlyRewards
	 * @param totalRewards
	 * @param transactions
	 */
	public RewardResponse(String customerId, String customerName, Map<String, Integer> monthlyRewards, int totalRewards,
			List<Transaction> transactions) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.monthlyRewards = monthlyRewards;
		this.totalRewards = totalRewards;
		this.transactions = transactions;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Map<String, Integer> getMonthlyRewards() {
		return monthlyRewards;
	}
	public void setMonthlyRewards(Map<String, Integer> monthlyRewards) {
		this.monthlyRewards = monthlyRewards;
	}
	public int getTotalRewards() {
		return totalRewards;
	}
	public void setTotalRewards(int totalRewards) {
		this.totalRewards = totalRewards;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
