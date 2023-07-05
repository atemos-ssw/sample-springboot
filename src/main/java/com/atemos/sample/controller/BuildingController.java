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
import com.atemos.sample.service.BuildingService;
import com.atemos.sample.service.LocationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/building")
@SuppressWarnings("rawtypes")
public class BuildingController {
	
	private final BuildingService service; 
	
	@Autowired
	public BuildingController(BuildingService service) {
		this.service = service;
	}
	
	@GetMapping("/getAll")
	public List<Building>  getAllData()   throws Exception {
		List<Building> list = service.onGetAll(); 
		return list;
	}   
	
	@PostMapping("/create")
	public Building create(@RequestBody Building building) throws Exception {
		Building b = service.onSave(building);
		log.info("[create] building: " + building.toString());
		return b;
	} 
	
	@PostMapping("/update")
	public Building update(@RequestBody Building building)  throws Exception{
		Building obj = service.onUpdate(building);
		log.info("[update] building: "+building.toString());
		return obj;
	} 
}
