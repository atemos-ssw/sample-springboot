package com.atemos.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atemos.sample.custom.BasicResponse;
import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.custom.RetiBody;
import com.atemos.sample.entity.Building;
import com.atemos.sample.entity.Location;
import com.atemos.sample.exception.ErrorCode;
import com.atemos.sample.exception.ErrorResponse;
import com.atemos.sample.service.LocationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/location")

public class LocationController {
	
	private final LocationService servce; 
	@Autowired
	public LocationController(LocationService servce) {
		this.servce = servce;
	}
	@GetMapping("/getAll")
	public ResponseEntity getAllData() {
		List<Location> list = servce.onGetAll();
		CommonResponse response= new CommonResponse<>();
		response.setData(list);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
	
	@PostMapping("/create")
	public ResponseEntity<BasicResponse> create(@RequestBody Location location) {
		Location loc = servce.onSave(location); 
		CommonResponse response = new CommonResponse<>();
		response.setData(loc);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity update(@RequestBody Location location){
		Location loc = servce.onUpdate(location);
		CommonResponse response = new CommonResponse<>();
		response.setData(loc);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
}
