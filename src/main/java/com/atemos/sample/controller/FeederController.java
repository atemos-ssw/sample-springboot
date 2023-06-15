package com.atemos.sample.controller;
 
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate; 

import lombok.extern.slf4j.Slf4j;

import com.atemos.sample.entity.CustomResponseEntity;
import com.atemos.sample.scheduler.JobFetchSensor;


@Slf4j
@RestController
@RequestMapping("api")
//@RestController(value = "/api")
public class FeederController {

	@Value("${gateway.api.key}")
	private String API_KEY;
	@Value("${gateway.auth.key}")
	private String AUTH_KEY;
 
	@RequestMapping(value="/api/test", method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String test() {
		return "{\"hello\":\"OK\"}";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<CustomResponseEntity> defaultMsg() {
		
		try {
			RestTemplate restTemplate = new RestTemplate();

			// Header set
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	 
			String Key = API_KEY + "__" + AUTH_KEY;

			CustomResponseEntity msg = new CustomResponseEntity(Key, "OK");
			
			HttpHeaders responseHeaders = new HttpHeaders();
			 
			ResponseEntity<CustomResponseEntity> responseEntity = new ResponseEntity<>(msg, responseHeaders, HttpStatus.OK);
			log.debug(responseEntity.toString());
			return responseEntity;
		}catch(Exception e) {
			
			ResponseEntity response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			log.error("error :%s".format(e.getMessage()));
			return response;
		}
		finally {
			// TODO: handle finally clause
			log.debug("success");
		}
	}
}
