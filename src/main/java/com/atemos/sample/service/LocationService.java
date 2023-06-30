package com.atemos.sample.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atemos.sample.Custom.BasicResponse;
import com.atemos.sample.Custom.CommonResponse;
import com.atemos.sample.entity.Location;
import com.atemos.sample.repository.LocationRepository;

@Service
public class LocationService {

	private final LocationRepository locationRepository ;
	
	@Autowired
	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@Transactional
	public BasicResponse onSave(final Location location) {
		
		CommonResponse commonResponse = new CommonResponse<>();
		Optional<Location> list = locationRepository.findByLocationId(location.getLocationId());
		Location loc;
		
		if(list.isPresent()) {
			commonResponse.setData(null);
			commonResponse.setHttpCode(HttpStatus.CONFLICT);
			commonResponse.setMessage("data is exited");
		}else {
			loc  = locationRepository.save(location);	
			commonResponse.setData(list);
			commonResponse.setHttpCode(HttpStatus.OK);
			commonResponse.setMessage("success");
		}
		return commonResponse;
	}
	
	@Transactional
	public BasicResponse onUpdate(final Location location) {
		Optional<Location> list = locationRepository.findByLocationId(location.getLocationId());
		CommonResponse commonResponse = new CommonResponse<>();
		
		Location loc;
		if(list.isPresent()) {
			locationRepository.save(location);	
			commonResponse.setHttpCode(HttpStatus.OK);
		}else {
			commonResponse.setHttpCode(HttpStatus.NOT_EXTENDED);
			commonResponse.setMessage("Data is not exiteded");
		}
		return commonResponse;
	}
}
