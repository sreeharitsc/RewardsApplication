/**
 * 
 */
package com.infosys.rewardsapplication.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.rewardsapplication.model.Transaction;

/**
 * @author sreehari
 *	interface used to represent Transaction Repository
 */
public interface TransactionRepository extends JpaRepository<Transaction, String> {

	List<Transaction> findByCustomerIdAndDateBetween(String customerId, LocalDate startDate, LocalDate endDate);
}
