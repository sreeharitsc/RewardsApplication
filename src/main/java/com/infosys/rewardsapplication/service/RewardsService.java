/**
 * 
 */
package com.infosys.rewardsapplication.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.rewardsapplication.dto.RewardResponse;
import com.infosys.rewardsapplication.exception.ResourceNotFoundException;
import com.infosys.rewardsapplication.model.Customer;
import com.infosys.rewardsapplication.model.Transaction;
import com.infosys.rewardsapplication.model.repository.CustomerRepository;
import com.infosys.rewardsapplication.model.repository.TransactionRepository;

/**
 * @author sreehari
 * class used to represent business logic for customer rewards
 */
@Service
public class RewardsService {

	private static final Logger logger = LoggerFactory.getLogger(RewardsService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public RewardResponse calculateRewards(String customerId, LocalDate start, LocalDate end){
		
		logger.debug("calculating rewards for customer with customer id {}, start date {}, end date {}", customerId, start, end);
		
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer with ID "+customerId+" is not found"));
		
		Map<String, Integer> monthlyRewards = new HashMap<>();
		int totalRewards = 0;
		
		List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, start, end);
		
		for(Transaction tx:transactions) {
			int points = calculatePoints(tx.getAmount());
			String month = tx.getDate().getMonth().toString();
			monthlyRewards.put(month, monthlyRewards.getOrDefault(month, 0)+ points);
			totalRewards+=points;
			logger.debug("transactions {} -> amount: {}, points {}", tx, tx.getDate(), tx.getAmount());
		}
		
		return new RewardResponse(customer.getId(), customer.getName(), monthlyRewards, totalRewards, transactions);
	}
	
	private int calculatePoints(Double amount) {
		int points = 0;
		if(amount > 100) {
			points+=(amount.intValue() - 100) * 2;
			points += 50;
		}else {
			points += (amount.intValue() - 50);
		}
		return points;
	}
}
