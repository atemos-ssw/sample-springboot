package com.atemos.sample.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.atemos.sample.Custom.CommonResponse;
import com.atemos.sample.Custom.ErrorResponse; 

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api")
public class ApiController {

	@Value("${gateway.api.key}")
	private String API_KEY;
	@Value("${gateway.auth.key}")
	private String AUTH_KEY;
	@Value("${gaeway.api.url}")
	private String GATEWAY_URL;

	@GetMapping("/site/{siteId}")
	public ResponseEntity<Object> siteInfo(@PathVariable("siteId") String siteId,
			@RequestParam("key") String key) {

		try {
			String url = "https://dev.energywatch.kr/api/v2/site/" + siteId + "?key=" + key;

			log.debug("url is %s".format(url));

			WebClient webClient = WebClient.builder().baseUrl(url).defaultHeader("accept", "application/json").defaultHeader("authentication", AUTH_KEY) 
					.build();

			ResponseEntity entity = webClient.get().retrieve().toEntity(Map.class).block();
			log.info(entity.getBody().toString());
			CommonResponse commonResponse = new CommonResponse<>();
			commonResponse.setData(entity.getBody());
			commonResponse.setHttpCode(HttpStatus.OK);
			commonResponse.setMessage("OK");
			
			return ResponseEntity.ok().body(commonResponse);
		} catch (Exception e) {
			// TODO: handle exception
			ErrorResponse errorResponse = new ErrorResponse("error", HttpStatus.INTERNAL_SERVER_ERROR);
			log.error(e.getLocalizedMessage());
			return ResponseEntity.ok().body(errorResponse);
		}
		
	}
}
