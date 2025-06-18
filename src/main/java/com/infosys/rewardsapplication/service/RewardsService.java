/**
 * 
 */
package com.infosys.rewardsapplication.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infosys.rewardsapplication.exception.RewardsException;
import com.infosys.rewardsapplication.model.Customer;
import com.infosys.rewardsapplication.model.Transaction;

/**
 * @author sreehari
 * class used to represent business logic for customer rewards
 */
@Service
public class RewardsService {

	private static final Logger logger = LoggerFactory.getLogger(RewardsService.class);
	
	// mock dataset for customer transactions
	private static final List<Customer> CUSTOMERS = new ArrayList<>();
	private static final List<Transaction> TRANSACTIONS = new ArrayList<>();
	
	static {
		TRANSACTIONS.add(new Transaction(LocalDate.of(2025,3,10),120.0));
		TRANSACTIONS.add(new Transaction(LocalDate.of(2025,3,10),120.0));
		TRANSACTIONS.add(new Transaction(LocalDate.of(2025,3,10),120.0));
		CUSTOMERS.add(new Customer(1L, "Hari", TRANSACTIONS));
		
		TRANSACTIONS.clear();
		
		TRANSACTIONS.add(new Transaction(LocalDate.of(2025,3,20),60.0));
		TRANSACTIONS.add(new Transaction(LocalDate.of(2025,4,25),40.0));
		TRANSACTIONS.add(new Transaction(LocalDate.of(2025,5,15),200.0));
		CUSTOMERS.add(new Customer(2L, "Sree", TRANSACTIONS));
		
	}
	
	public ResponseEntity<?> calculateRewards(Long customerId, LocalDate start, LocalDate end){
		
		logger.debug("calculating rewards for customer with customer id {}, start date {}, end date {}", customerId, start, end);
		Customer customer = CUSTOMERS.stream().filter(c -> c.getId() == customerId).findFirst().orElseThrow(() -> {logger.error("customer with id {} not found",customerId); return new RewardsException("customer with ID "+customerId+" is not found");});
		
		Map<String, Integer> monthlyRewards = new HashMap<>();
		int totalRewards = 0;
		
		List<Transaction> transactions = customer.getTransactions().stream().filter(trans -> !trans.getDate().isBefore(start) && !trans.getDate().isAfter(end)).collect(Collectors.toList());
		
		for(Transaction tx:transactions) {
			int points = calculatePoints(tx.getAmount());
			String month = tx.getDate().getMonth().toString();
			monthlyRewards.put(month, monthlyRewards.getOrDefault(month, 0)+ points);
			totalRewards+=points;
			logger.debug("transactions {} -> amount: {}, points {}", tx, tx.getDate(), tx.getAmount());
		}
		
		Map<String, String> response = new HashMap<>();
		response.put("customerId", customer.getId().toString());
		response.put("customerName", customer.getName());
		response.put("startDate", start.toString());
		response.put("endDate", end.toString());
		response.put("transactions", transactions.toString());
		response.put("monthlyRewards", monthlyRewards.toString());
		response.put("totalRewards", String.valueOf(totalRewards));
		return new ResponseEntity<>(response,HttpStatus.OK);
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
