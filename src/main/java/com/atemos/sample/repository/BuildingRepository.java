package com.atemos.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atemos.sample.entity.Building; 

public interface BuildingRepository extends JpaRepository<Building, Integer>  {

	Optional<Building> findByBuildingId(Integer buildingId);

}
