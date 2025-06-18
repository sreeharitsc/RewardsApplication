/**
 * 
 */
package com.infosys.rewardsapplication.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.infosys.rewardsapplication.RewardsapplicationApplication;

/**
 * @author sreehari
 * unit test class for rewards controller
 */

@SpringBootTest(classes = RewardsapplicationApplication.class)
@AutoConfigureMockMvc
public class RewardsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testCustomerNotFound() throws Exception {
		mockMvc.perform(get("/api/v1/rewards/111").param("start", "2025-03-01").param("end", "2025-05-31"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("customer with ID 111 is not found"));
	}
}
