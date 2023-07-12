package com.atemos.sample.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.entity.Location;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/public")
public class SampleController {

	@GetMapping("/sample")
	public ResponseEntity getAllData() {
		CommonResponse r = new CommonResponse();
		r.setData("ss");
		
		return new ResponseEntity<>(r, HttpStatus.OK);
	} 
	
}
