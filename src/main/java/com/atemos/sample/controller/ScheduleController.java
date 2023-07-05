package com.atemos.sample.controller;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.atemos.sample.custom.BasicResponse;
import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.custom.ScheduleRequest;
import com.atemos.sample.scheduler.JobFetchSensor;
import com.atemos.sample.service.ScheduleService;

@Slf4j
@RestController
@RequestMapping("api")
//@RestController(value = "/api")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping("/createSchedule")
	public ResponseEntity<String> createSchedule(@RequestBody(required = true) Optional<ScheduleRequest> request) {
		ScheduleRequest scheduleRequest = request.get();
		try {

			if (request.isPresent() && scheduleRequest.nullCheck()) {
				scheduleService.scheduleJob(scheduleRequest.getJobName(), scheduleRequest.getCronExpression(),
						scheduleRequest.getItems(), scheduleRequest.getSiteId(), scheduleRequest.getGroupId(),
						scheduleRequest.getLogicalId());

				return ResponseEntity.ok("Schedule created successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
			}

		} catch (SchedulerException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to create %s schedule.".format(scheduleRequest.getJobName()));
		}
	}

	@PostMapping("/pauseSchedule")
	public ResponseEntity<String> pauseSchedule(@RequestBody ScheduleRequest request) {
		try {

			scheduleService.pauseJob(request.getJobName());
			return ResponseEntity.ok(String.format("{0} Schedule paused successfully.", request.getJobName()));
		} catch (SchedulerException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to pause %s schedule.".format(request.getJobName()));
		}
	}

	@PostMapping("/removeSchedule")
	public ResponseEntity<String> removeSchedule(@RequestBody ScheduleRequest request) {
		try {
			scheduleService.unscheduleJob(request.getJobName());
			return ResponseEntity.ok(String.format("{0} Schedule removed successfully.", request.getJobName()));
		} catch (SchedulerException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to removed %s schedule.".format(request.getJobName()));
		}
	}

	
}
