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
import com.atemos.sample.service.BuildingService;
import com.atemos.sample.service.LocationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/building")

public class BuildingController {
	
	private final BuildingService service; 
	@Autowired
	public BuildingController(BuildingService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<BasicResponse> createBuilding(@RequestBody Building location) {
		try {
			BasicResponse basicResponse = service.onSave(location);

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
	public ResponseEntity<BasicResponse> updateBuilding(@RequestBody Building location){
		try {
			BasicResponse basicResponse = service.onUpdate(location);
			return ResponseEntity.ok().body(basicResponse); 
		} catch (Exception e) {
			// TODO: handle exception
			ErrorResponse commonResponse = new ErrorResponse(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
			
			return ResponseEntity.internalServerError().body(commonResponse);
		}
	} 
}
