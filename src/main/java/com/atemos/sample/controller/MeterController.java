package com.atemos.sample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atemos.sample.custom.CommonResponse; 
import com.atemos.sample.entity.Meter;
import com.atemos.sample.service.MeterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/api/meter")
public class MeterController {

	private final MeterService meterService;

	@Autowired
	public MeterController(MeterService meterService) {
		this.meterService = meterService;
	}

	@GetMapping("/getAll")
	public ResponseEntity getAllData() {
		List<Meter> list = meterService.onGetAll();
		CommonResponse response = new CommonResponse<>();
		response.setData(list);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/get/{meterId}")
	public ResponseEntity getData(@PathVariable("meterId") Integer id) {
		Optional<Meter> optM = meterService.onGet(id);

		CommonResponse response = new CommonResponse<>();
		response.setData(optM);
		return new ResponseEntity(response, HttpStatus.OK);	 	
	}
	
	@PostMapping("/create")
	public ResponseEntity create(@RequestBody Meter m) {
		Meter mm  = meterService.onSave(m);
		CommonResponse response = new CommonResponse<>();
		response.setData(mm);
		return new ResponseEntity(response, HttpStatus.OK);	
	}
	
	@PostMapping("/update")
	public ResponseEntity update(@RequestBody Meter m) {
		Meter mm = meterService.onUpdate(m);
		CommonResponse response = new CommonResponse<>();
		response.setData(mm);
		return new ResponseEntity(response, HttpStatus.OK);	
	}
}
