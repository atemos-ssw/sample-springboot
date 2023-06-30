package com.atemos.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atemos.sample.entity.Location;

 
public interface LocationRepository extends JpaRepository<Location, Integer> {
//	@Override
//	default Optional<Location> findById(Integer id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
	
	Optional<Location> findByLocationId(Integer id);
}
