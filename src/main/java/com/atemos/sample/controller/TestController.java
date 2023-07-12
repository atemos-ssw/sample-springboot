package com.atemos.sample.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.atemos.sample.custom.ScheduleRequest;
import com.atemos.sample.service.ScheduleService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/public/")
public class TestController {

	@Value("${gateway.api.key}")
	private String API_KEY;
	@Value("${gateway.auth.key}")
	private String AUTH_KEY;
	@Value("${gaeway.api.url}")
	private String GATEWAY_URL;

	@PostMapping("/test")
	public ResponseEntity<Object> createSchedule(@RequestBody(required = true) Optional<ScheduleRequest> request) {
		ScheduleRequest scheduleRequest = request.get();
		try {

			if (request.isPresent() && scheduleRequest.nullCheck()) {

				// 현재 시간 가져오기
				Instant now = Instant.now();
				// 1초 빼기
				Instant minusOneSecond = now.minusSeconds(60 * 60 * 24 * 7);
				LocalDateTime localDateTime1 = LocalDateTime.ofInstant(now, ZoneOffset.systemDefault());
				LocalDateTime localDateTime2 = LocalDateTime.ofInstant(minusOneSecond, ZoneOffset.systemDefault());
 
				// Instant를 나노초로 변환
//	             String end_ts = Long.toString(now.toEpochMilli() * 1000000);
//	             String start_ts = Long.toString(minusOneSecond.toEpochMilli()*1000000); 

				String start_ts = "1649696400000000";
				String end_ts = "1649782799999000";

				String url = GATEWAY_URL.replace("{start_ts}", start_ts).replace("{end_ts}", end_ts)
						.replace("{logicalId}", scheduleRequest.getLogicalId())
						.replace("{groupId}", scheduleRequest.getGroupId())
						.replace("{siteId}", scheduleRequest.getSiteId()).replace("{item}", scheduleRequest.getItems());

				log.debug("url is %s".format(url));
				
				log.debug("AUTH_KEY :" + AUTH_KEY);

//	     		WebClient webClient = WebClient.builder().defaultHeader("authentication", AUTH_KEY).defaultHeader("accept", "application/json").baseUrl(url).build();  
//	     		webClient.head().attribute("authentication",AUTH_KEY).attribute("accept", "application/json");

//				WebClient webClient = WebClient.create();
//				webClient.get().uri(url).header("accept", "application/json").header("authentication", AUTH_KEY);
				
				
				WebClient webClient = WebClient.builder()
						.filters(exchangeFilterFunctions -> {
						      exchangeFilterFunctions.add(logRequest());
//						      exchangeFilterFunctions.add(logResponse());
						  })
						.baseUrl(url).defaultHeader("accept", "application/json").defaultHeader("authentication", AUTH_KEY).build();
				
				ResponseEntity entity = webClient.get().retrieve().toEntity(Map.class).block();
				log.info(entity.getBody().toString());
				return ResponseEntity.ok().body(entity.getBody());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
			}

		} catch (Exception e) {
			log.error("error " +  e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to create %s schedule.".format(scheduleRequest.getJobName()));
		}
	}
	
	ExchangeFilterFunction logRequest() {
	    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
	        if (log.isDebugEnabled()) {
	            StringBuilder sb = new StringBuilder("Request: \n");
	            //append clientRequest method and url
	            clientRequest
	              .headers()
	              .forEach((name, values) -> values.forEach(value -> log.debug(String.format("%s : %s", name.toString(), value.toString()))));
	            
	        }
	        return Mono.just(clientRequest);
	    });
	}
	
}
