package com.atemos.sample.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atemos.sample.entity.Building;
import com.atemos.sample.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>  {
 
	Optional<Customer> findByCustomerId(Integer customerId);

	 
	
}
