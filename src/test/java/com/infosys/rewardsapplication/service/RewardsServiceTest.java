/**
 * 
 */
package com.infosys.rewardsapplication.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.infosys.rewardsapplication.exception.RewardsException;

/**
 * @author sreehari
 *
 *	unit test class for RewardsService class
 */
public class RewardsServiceTest {

	private final RewardsService rewardsService = new RewardsService();
	
	@Test
	void testInvalidCustomerThrowsException() {
		assertThrows(RewardsException.class, () -> rewardsService.calculateRewards(11L, LocalDate.now().minusMonths(1), LocalDate.now()));
	}
}
