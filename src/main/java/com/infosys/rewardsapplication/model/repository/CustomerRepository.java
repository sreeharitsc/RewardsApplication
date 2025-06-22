/**
 * 
 */
package com.infosys.rewardsapplication.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.rewardsapplication.model.Customer;

/**
 * @author sreehari
 *
 *	interface used to represent repository for customer class
 */
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
