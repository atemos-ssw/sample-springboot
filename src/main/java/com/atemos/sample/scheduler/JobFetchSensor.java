package com.atemos.sample.scheduler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobFetchSensor implements Job {

	

	@Value("${gateway.api.key}")
	private String API_KEY;
	@Value("${gateway.auth.key}")
	private String AUTH_KEY;
	@Value("${gaeway.api.url}")
	private String GATEWAY_URL;
  
	
    @SuppressWarnings({ "unused", "static-access" })
	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	 JobDataMap dataMap = context.getMergedJobDataMap();
    	 String items = dataMap.getString("items");
    	 String siteId = dataMap.getString("siteId");
    	 String groupId = dataMap.getString("groupId");
    	 String logicalId= dataMap.getString("logicalId");
        // 스케줄링할 작업 내용 작성 
//    	log.debug("context"  + context.toString());
    	 
    	 
    	 
//    	 String item = "average_phase_current";
 		
		 // 현재 시간 가져오기
        Instant now = Instant.now(); 
        // 1초 빼기
        Instant minusOneSecond = now.minusSeconds(60*60*24*7); 
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(now, ZoneOffset.systemDefault());
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(minusOneSecond, ZoneOffset.systemDefault());
        
        log.info(localDateTime1.toString());
        log.info(localDateTime2.toString());
        // Instant를 나노초로 변환
//        String end_ts = Long.toString(now.toEpochMilli() * 1000000);
//        String start_ts = Long.toString(minusOneSecond.toEpochMilli()*1000000); 
        
        String start_ts = "1649696400000000000";
        String end_ts="1649782799999000000";
        
		String url = GATEWAY_URL
				.replace("{start_ts}", start_ts )
				.replace("{end_ts}", end_ts)
				.replace("{logicalId}", logicalId)
				.replace("{groupId}", groupId)
				.replace("{siteId}", siteId)
				.replace("{item}", items);
		
		log.debug("url is %s".format(url));
		
		
		WebClient webClient = WebClient.builder().defaultHeader("authentication", AUTH_KEY).defaultHeader("accept", "application/json").baseUrl(url).build();
		
		try {
			@SuppressWarnings("rawtypes")
			ResponseEntity entity = webClient.get().retrieve().toEntity(Map.class).block();
			log.info(entity.getBody().toString());
//			return ResponseEntity.ok(entity);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error :%s".format(e.toString()));
//			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
		} 
		
		
    	 log.debug("items is %s, siteId is %s, groupId is %s".format(items, siteId, groupId));
    }

}