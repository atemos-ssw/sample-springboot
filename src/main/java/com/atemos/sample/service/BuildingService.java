package com.atemos.sample.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atemos.sample.Custom.BasicResponse;
import com.atemos.sample.Custom.CommonResponse;
import com.atemos.sample.entity.Building;
import com.atemos.sample.repository.BuildingRepository;
import com.atemos.sample.repository.LocationRepository;

@Service
public class BuildingService {

	private final BuildingRepository buildingRepository; 
	
	@Autowired
	public BuildingService(BuildingRepository buildingRepository) {
		this.buildingRepository = buildingRepository;
	}
	
	@Transactional
	public BasicResponse onSave(final Building building) {
		Building buidling;
		CommonResponse commonResponse = new CommonResponse<>();
		Optional<Building> list = buildingRepository.findByBuildingId(building.getBuildingId());
		if(list.isPresent()) {
			
		}else {
			
		}
		
		return commonResponse;
	}
	
	@Transactional
	public BasicResponse onUpdate(final Building building) {
		return null;
	} 
}
