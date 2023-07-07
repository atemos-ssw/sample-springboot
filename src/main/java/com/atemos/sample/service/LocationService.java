package com.atemos.sample.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atemos.sample.custom.BasicResponse;
import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.entity.Location;
import com.atemos.sample.exception.ErrorCode;
import com.atemos.sample.exception.GlobalException;
import com.atemos.sample.repository.LocationRepository;

@Service
public class LocationService {

	private final LocationRepository locationRepository ;
	
	@Autowired
	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Location onSave(final Location location) {
		
		 Optional<Location> list = locationRepository.findByLocationId(location.getLocationId());
		Location loc;
		
		if(list.isEmpty()) {
			throw new GlobalException("Data not founded",ErrorCode.NOT_FOUND);
		}else {
			loc  = locationRepository.save(location);				
		}
		return loc;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Location onUpdate(final Location location) {
		Optional<Location> list = locationRepository.findByLocationId(location.getLocationId());
  	
		Location loc;
		if(list.isPresent()) {
			loc = locationRepository.save(location);	 
		}else {
			throw new GlobalException("Data not founded",ErrorCode.NOT_FOUND);
		}
		return loc;
	}

	@Transactional
	public List<Location> onGetAll() {
		List<Location> list = locationRepository.findAll();
		return list;
	}
	
	@Transactional
	public Optional<Location> onGet(final Integer locationId) {
		Optional<Location> list = locationRepository.findByLocationId(locationId);
		return list;
	}
}
