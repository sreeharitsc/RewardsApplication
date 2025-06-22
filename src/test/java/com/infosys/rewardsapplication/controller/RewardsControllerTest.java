/**
 * 
 */
package com.infosys.rewardsapplication.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.infosys.rewardsapplication.RewardsapplicationApplication;
import com.infosys.rewardsapplication.dto.RewardResponse;
import com.infosys.rewardsapplication.model.Transaction;
import com.infosys.rewardsapplication.service.RewardsService;



/**
 * @author sreehari
 * unit test class for rewards controller
 */

@SpringBootTest(classes = RewardsapplicationApplication.class)
@AutoConfigureMockMvc
public class RewardsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RewardsService rewardsService;
	
	@Test
	void testValidRequest() throws Exception{
		String customerId="C001";
		LocalDate startDate = LocalDate.of(2025, 4, 1);
		LocalDate endDate = LocalDate.of(2025, 6, 30);
		
		List<Transaction> transactions = new ArrayList<>();
		Transaction t1 = new Transaction("T001", LocalDate.of(2025, 5, 1), 120.0,  null);
		Transaction t2 = new Transaction("T002", LocalDate.of(2025, 6, 1), 75.0,  null);
		transactions.add(t1);
		transactions.add(t2);
		
		Map<String, Integer> monthlyRewards = new HashMap<>();
		monthlyRewards.put("May", 90);
		monthlyRewards.put("Jun", 25);
		
		RewardResponse respose = new RewardResponse(customerId, "hari", monthlyRewards, 115, transactions);
		
		when(rewardsService.calculateRewards(customerId, startDate, endDate)).thenReturn(respose);
		
		mockMvc.perform(get("/api/v1/rewards/{customerId}", customerId)
				.param("start", "2025-04-01")
				.param("end", "2025-06-30"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalRewards").value("115"));
	}
	@Test
	void testInvlaidDateFormatExcetion() throws Exception {
		mockMvc.perform(get("/api/v1/rewards/C001").param("start", "2025-06-AB").param("end", "2025-05-31"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errorMessage").value("invalid date format, please use yyyy-MM-dd format"));
	}
	
	@Test
	void testInvlaidDateInputExcetion() throws Exception {
		mockMvc.perform(get("/api/v1/rewards/C001").param("start", "2025-07-01").param("end", "2025-06-01"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errorMessage").value("start date must be before or equal to end date"));
	}
	
	@Test
	void testCustomerNotFound() throws Exception{
		String customerId="100";
		when(rewardsService.calculateRewards(customerId, LocalDate.parse("2025-03-31"), LocalDate.parse("2025-05-31"))).thenThrow(
				new RuntimeException("customer with ID "+customerId+" is not found"));
		
		mockMvc.perform(get("/api/v1/rewards/{customerId}", customerId)
				.param("startDate", "2025-03-31")
				.param("endDate", "2025-05-31")
				).andExpect(status().isInternalServerError());
	}
	
}
