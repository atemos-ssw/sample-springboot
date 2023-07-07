package com.atemos.sample.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.custom.RetiBody;
import com.atemos.sample.exception.ErrorCode;
import com.atemos.sample.exception.ErrorResponse;

import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;

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
			@RequestParam("key") String key){

		try {
			String url = "https://dev.energywatch.kr/api/v2/site/" + siteId + "?key=" + key;

			log.debug("url is %s".format(url));

			WebClient webClient = WebClient.builder().baseUrl(url).defaultHeader("accept", "application/json").defaultHeader("authentication", AUTH_KEY) 
					.build();

			ResponseEntity<RetiBody> entity = webClient.get().retrieve().toEntity(RetiBody.class).block();
			RetiBody retiBody = entity.getBody();			
			log.info(retiBody.get_data_().toString());			
			CommonResponse commonResponse = new CommonResponse<>();
			commonResponse.setData(retiBody.get_data_());
			
			commonResponse.setHttpCode(HttpStatus.OK);
			commonResponse.setMessage(retiBody.getMessage());
			
			return ResponseEntity.ok().body(entity.getBody());
		} catch (Exception e) {
			// TODO: handle exception
//			ErrorResponse errorResponse = new ErrorResponse("error", HttpStatus.INTERNAL_SERVER_ERROR);
			ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
			log.error(e.getLocalizedMessage());
//			return ResponseEntity.ok().body(errorResponse);
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@GetMapping("/site/acfeeder/{siteId}")
	public ResponseEntity acFeeder(@PathVariable("siteId") String siteId,
			@RequestParam("key") String key, @RequestParam("groupId") String groupId, 
			@RequestParam("logicalId") String logicalId, @RequestParam("items") String items ){

		try {
			
			Instant end_ts = Instant.now();
			Instant start_ts = end_ts.minusSeconds(60*60);
			long endNT = end_ts.getEpochSecond() * 1_000_000_000L + end_ts.getNano();
			long startNT = start_ts.getEpochSecond() * 1_000_000_000L + start_ts.getNano();
	         
			
			String KEY = "key="+key;
			String GROUP_ID = "group_id="+groupId;
			String ITEMS = "item="+items;
			String START_NT = "start_ts="+startNT;
			String END_NT = "end_ts="+endNT;
			String SITE_ID="site_id="+siteId;
			String LOGICAL_ID="metering_device_logical_id="+logicalId;
			
			StringBuilder sb = new StringBuilder(GATEWAY_URL+"/metering-ac/group");
			sb.append("?")
				.append(KEY).append("&")
				.append(SITE_ID).append("&")
				.append(GROUP_ID).append("&")
				.append(START_NT).append("&")
				.append(ITEMS).append("&")
				.append(LOGICAL_ID).append("&")
				.append(END_NT);
					
			String url = sb.toString(); 
			log.debug("url is %s".format(url));

			WebClient webClient = WebClient.builder().baseUrl(url).defaultHeader("accept", "application/json").defaultHeader("authentication", AUTH_KEY) 
					.build();

			ResponseEntity<RetiBody> entity = webClient.get().retrieve().toEntity(RetiBody.class).block();
			RetiBody retiBody = entity.getBody();			
			log.info("body is " + retiBody.get_data_().toString());			
			CommonResponse commonResponse = new CommonResponse<>();
			commonResponse.setData(retiBody.get_data_());
			
			commonResponse.setHttpCode(HttpStatus.OK);
			commonResponse.setMessage(retiBody.getMessage());
			
			return ResponseEntity.ok(commonResponse);
		} catch (Exception e) {
			// TODO: handle exception
//			ErrorResponse errorResponse = new ErrorResponse("error", HttpStatus.INTERNAL_SERVER_ERROR);
//			log.error(e.getLocalizedMessage());
//			return ResponseEntity.ok().body(errorResponse); 
			ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
			log.error(e.getLocalizedMessage());
//			return ResponseEntity.ok().body(errorResponse);
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	} 
}
