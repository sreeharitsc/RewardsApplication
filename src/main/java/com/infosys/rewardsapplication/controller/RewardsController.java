/**
 * 
 */
package com.infosys.rewardsapplication.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.rewardsapplication.dto.RewardResponse;
import com.infosys.rewardsapplication.exception.InvalidDateFormatException;
import com.infosys.rewardsapplication.exception.InvalidDateInputException;
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
	public RewardResponse getRewards(@PathVariable String customerId, @RequestParam String start, @RequestParam String end){
		
		logger.debug("received request for customer with customerId {}, start date {}, end date {}",customerId, start, end);
		LocalDate startDate = null;
		LocalDate endDate = null;
		
		// invalid date format validation
		try {
			startDate = LocalDate.parse(start);
			endDate = LocalDate.parse(end);
		} catch (DateTimeParseException e) {
			// TODO Auto-generated catch block
			logger.error("invalid date format, please use yyyy-MM-dd format");
			throw new InvalidDateFormatException("invalid date format, please use yyyy-MM-dd format");
		}
		
		// invalid date input validation
		if(startDate.isAfter(endDate)) {
			logger.error("start date {} must be before or equal to end date {}", start, end);
			throw new InvalidDateInputException("start date must be before or equal to end date");
		}
		
		return rewardsService.calculateRewards(customerId, startDate, endDate);
	}
}
