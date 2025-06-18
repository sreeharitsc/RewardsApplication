/**
 * 
 */
package com.infosys.rewardsapplication.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.rewardsapplication.exception.RewardsException;
import com.infosys.rewardsapplication.service.RewardsService;

/**
 * @author sreehari
 * class used to represent rest controller for customer rewards application logic
 */
@RestController
@RequestMapping("/api/v1/rewards")
public class RewardsController {

	private static final Logger logger = LoggerFactory.getLogger(RewardsController.class);
	
	@Autowired
	private RewardsService rewardsService;
	
	@GetMapping("/{customerId}")
	public ResponseEntity<?> getRewards(@PathVariable Long customerId, @RequestParam String start, @RequestParam String end){
		
		logger.debug("received request for customer with customerId {}, start date {}, end date {}",customerId, start, end);
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		
		// date input validation
		if(startDate.isAfter(endDate)) {
			logger.error("start date {} must be before or equal to end date {}", start, end);
			throw new RewardsException("start date must be before or equal to end date");
		}
		
		return rewardsService.calculateRewards(customerId, startDate, endDate);
	}
}
