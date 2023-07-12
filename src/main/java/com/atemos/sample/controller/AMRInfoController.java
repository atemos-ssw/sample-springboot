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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.entity.AmrInfo;
import com.atemos.sample.entity.Building;
import com.atemos.sample.service.AMRInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
@SuppressWarnings("rawtypes")
@RequestMapping("/api/v1/amrinfo")
public class AMRInfoController {

	private final AMRInfoService amrInfoService;
	
	@Autowired
	public AMRInfoController(AMRInfoService amrInfoService) {
		// TODO Auto-generated constructor stub
		this.amrInfoService = amrInfoService;
	}
	 
	@SuppressWarnings("unchecked")
	@GetMapping("/getAll")
	public ResponseEntity getAllData() {
		List<AmrInfo> list = amrInfoService.onGetAll();
		CommonResponse response = new CommonResponse<>();
		response.setData(list);
		return new ResponseEntity(response, HttpStatus.OK);	 	
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/get/{serverId}")
	public ResponseEntity getData(@PathVariable("serverId") Integer serverId) {
		Optional<AmrInfo> optAmrInfo = amrInfoService.onGetServerId(serverId);
		CommonResponse response = new CommonResponse();
		response.setData(optAmrInfo);
		return new ResponseEntity(response, HttpStatus.OK);	 
	}

	@PostMapping("/create")
	public ResponseEntity create(@RequestBody AmrInfo amrInfo){
		AmrInfo object = amrInfoService.onSave(amrInfo);
		CommonResponse response = new CommonResponse<>();
		response.setData(object);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity update(@RequestBody AmrInfo amrInfo)  throws Exception{
		AmrInfo object = amrInfoService.onUpdate(amrInfo); 
		CommonResponse response = new CommonResponse<>();
		response.setData(amrInfo);
		return new ResponseEntity<>(response, HttpStatus.OK);
	} 
}
