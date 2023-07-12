package com.atemos.sample.controller;

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
import com.atemos.sample.entity.AuthToken;
import com.atemos.sample.service.AuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
@SuppressWarnings("rawtypes")
@RequestMapping("/api/v1//auth")

public class AuthController {

	private final AuthService authService;
	
	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/create")
	public ResponseEntity createJWT(@RequestBody AuthToken param) {
		AuthToken authToken  = authService.createJWT(param.getCustomerId());
		CommonResponse response = new CommonResponse<>();
		response.setData(authToken);
		return new ResponseEntity(response, HttpStatus.OK);	 
	}
}
