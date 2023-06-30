package com.atemos.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atemos.sample.Custom.BasicResponse;
import com.atemos.sample.Custom.CommonResponse;
import com.atemos.sample.Custom.ErrorResponse;
import com.atemos.sample.Custom.RetiBody;
import com.atemos.sample.entity.Building;
import com.atemos.sample.entity.Location;
import com.atemos.sample.service.LocationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/location")

public class LocationController {
	
	private final LocationService servce; 
	@Autowired
	public LocationController(LocationService servce) {
		this.servce = servce;
	}
	
	@PostMapping("/create")
	public ResponseEntity<BasicResponse> addLocation(@RequestBody Location location) {
		try {
			BasicResponse basicResponse = servce.onSave(location); 
			CommonResponse commonResponse = new CommonResponse<>();
			commonResponse.setHttpCode(HttpStatus.OK);			
			return ResponseEntity.ok().body(commonResponse);
		} catch (Exception e) {
			// TODO: handle exception
			ErrorResponse commonResponse = new ErrorResponse(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
			return ResponseEntity.internalServerError().body(commonResponse);
		}
	} 
	
	@PostMapping("/update")
	public ResponseEntity<BasicResponse> updateLocation(@RequestBody Location location){
		try {
			BasicResponse basicResponse = servce.onUpdate(location);
			return ResponseEntity.ok().body(basicResponse); 
		} catch (Exception e) {
			// TODO: handle exception
			ErrorResponse commonResponse = new ErrorResponse(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
			
			return ResponseEntity.internalServerError().body(commonResponse);
		}
	} 
}
