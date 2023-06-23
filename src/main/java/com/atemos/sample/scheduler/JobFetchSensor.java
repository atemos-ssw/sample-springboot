package com.atemos.sample.scheduler;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
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

import com.atemos.sample.Custom.CommonResponse;
import com.atemos.sample.Custom.RetiBody;

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
		String logicalId = dataMap.getString("logicalId");

		Instant end_ts = Instant.now();
		Instant start_ts = end_ts.minusSeconds(60 * 60);
		long endNT = end_ts.getEpochSecond() * 1_000_000_000L + end_ts.getNano();
		long startNT = start_ts.getEpochSecond() * 1_000_000_000L + start_ts.getNano();

		printTimeStamp(endNT);
		printTimeStamp(startNT);
		
		String KEY = "key=" + API_KEY;
		String GROUP_ID = "group_id=" + groupId;
		String ITEMS = "item=" + items;
		String START_NT = "start_ts=" + startNT;
		String END_NT = "end_ts=" + endNT;
		String SITE_ID = "site_id=" + siteId;
		String LOGICAL_ID = "metering_device_logical_id=" + logicalId;
		
		

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
		try { 
			ResponseEntity<RetiBody> entity = webClient.get().retrieve().toEntity(RetiBody.class).block();
			RetiBody retiBody = entity.getBody();			
			
			log.info("body is " + retiBody.get_data_().toString());			
			CommonResponse commonResponse = new CommonResponse<>();
			commonResponse.setData(retiBody.get_data_());
			
			commonResponse.setHttpCode(HttpStatus.OK);
			commonResponse.setMessage(retiBody.getMessage());
			log.info(commonResponse.toString());
//			return ResponseEntity.ok(commonResponse);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error :%s".format(e.toString()));
//			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
		}

		log.debug("items is %s, siteId is %s, groupId is %s".format(items, siteId, groupId));
	}

	public void printTimeStamp(long nanosecond) {
		if (nanosecond <= 0 || nanosecond == Long.MIN_VALUE) {
			System.out.println(String.format("%d is not nanoseconds", nanosecond));
		}

		// 나노초 단위의 시간 범위 내의 값인지 확인합니다.
		// 예를 들어, 1초는 1_000_000_000_000나노초입니다.
		// 특정 범위 내에 제한할 필요가 있다면, 해당 범위를 적용할 수 있습니다.
		if (nanosecond > 1_000_000_000_000L) {
			System.out.println(String.format("%d's range is over 1_000_000_000_000L", nanosecond));
		}

		long millis = nanosecond / 1_000_000L;
		Date date = new Date(millis);

		// Create a formatter

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

		String strDT = formatter.format(date);
//        System.out.println(String.format("%d is %s", nanosecond, strDT));
		log.debug(String.format("%d is %s", nanosecond, strDT));

	}
}