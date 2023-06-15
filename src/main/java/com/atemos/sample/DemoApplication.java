package com.atemos.sample;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	
	@PostConstruct
	void started(){
	    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
