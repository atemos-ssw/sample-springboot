package com.atemos.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atemos.sample.entity.Customer;
import com.atemos.sample.entity.Meter;

public interface MeterRepository  extends JpaRepository<Meter, Integer>  {
	 
		Optional<Meter> findByMeterId(Integer meterId);

		 
		
	}
