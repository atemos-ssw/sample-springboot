package com.atemos.sample.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atemos.sample.custom.BasicResponse;
import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.entity.Building;
import com.atemos.sample.entity.Location;
import com.atemos.sample.exception.ErrorCode;
import com.atemos.sample.exception.GlobalException;
import com.atemos.sample.repository.BuildingRepository;
import com.atemos.sample.repository.LocationRepository;

@Service
public class BuildingService {

	@Autowired
    EntityManager entityManager;
	
	private final BuildingRepository repository; 
	private final LocationRepository locationRepository;
	
	@Autowired
	public BuildingService(BuildingRepository repository, LocationRepository locationRepository) {
		this.repository = repository;
		this.locationRepository = locationRepository;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(rollbackOn = Exception.class)
//	@Transactional
	public Building onSave(final Building building) {
		Building b; 
		Optional<Building> list = repository.findByBuildingId(building.getBuildingId());
		if(list.isPresent()) {
			throw new GlobalException("data is duplicated",ErrorCode.INTER_SERVER_ERROR);
		}else {
			Optional<Location> optional = locationRepository.findByLocationId(building.getLocation().getLocationId());
			if(optional.isEmpty()) {
				throw new GlobalException("data is duplicated",ErrorCode.INTER_SERVER_ERROR);
			}
			b = repository.save(building);
			
		}
		return b;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Building onUpdate(final Building building) {
		Optional<Building> list = repository.findByBuildingId(building.getBuildingId());
		 
		Building loc;
		if(list.isPresent()) {
			repository.save(building);
		}else {
			throw new GlobalException("Data is not founded", ErrorCode.NOT_FOUND);
		}
		return building;
	}

	@Transactional
	public List<Building> onGetAll() {
		List<Building> list = repository.findAll();
		return list;
	} 
}
