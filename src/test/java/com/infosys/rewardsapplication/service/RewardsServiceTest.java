/**
 * 
 */
package com.infosys.rewardsapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infosys.rewardsapplication.dto.RewardResponse;
import com.infosys.rewardsapplication.exception.ResourceNotFoundException;
import com.infosys.rewardsapplication.model.Customer;
import com.infosys.rewardsapplication.model.Transaction;
import com.infosys.rewardsapplication.model.repository.CustomerRepository;
import com.infosys.rewardsapplication.model.repository.TransactionRepository;

/**
 * @author sreehari
 *
 *	unit test class for RewardsService class
 */
public class RewardsServiceTest {

	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private TransactionRepository transactionRepository;
	
	@InjectMocks
	private RewardsService rewardsService;
	
	private Customer customer;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customer = new Customer("C001", "Alice", null);
	}
	
	@Test
    void testCustomerNotFoundException() {
        when(customerRepository.findById("C999")).thenReturn(Optional.empty());

        LocalDate start = LocalDate.of(2025, 4, 1);
        LocalDate end = LocalDate.of(2025, 6, 30);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            rewardsService.calculateRewards("C999", start, end);
        });

        assertEquals("customer with ID C999 is not found", exception.getMessage());
    }
	
	@Test
    void testReturnsCorrectTotals() {
        // Given
        LocalDate start = LocalDate.of(2025, 4, 1);
        LocalDate end = LocalDate.of(2025, 6, 30);

        List<Transaction> transactions = new ArrayList<>();
        
		Transaction t1 = new Transaction("T001", LocalDate.of(2025, 5, 1), 120.0,  customer);
		Transaction t2 = new Transaction("T002", LocalDate.of(2025, 6, 1), 75.0,  customer);
		transactions.add(t1);
		transactions.add(t2);

        when(customerRepository.findById("C001")).thenReturn(Optional.of(customer));
        when(transactionRepository.findByCustomerIdAndDateBetween("C001", start, end)).thenReturn(transactions);

        // When
        RewardResponse response = rewardsService.calculateRewards("C001", start, end);

        // Then
        assertEquals("C001", response.getCustomerId());
        assertEquals("Alice", response.getCustomerName());
        assertEquals(2, response.getMonthlyRewards().size());
        assertEquals(115, response.getTotalRewards());

    }
	
}
