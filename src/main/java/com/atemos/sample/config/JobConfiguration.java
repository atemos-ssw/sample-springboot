package com.atemos.sample.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static org.quartz.JobBuilder.newJob;
import com.atemos.sample.scheduler.JobFetchSensor;

@Configuration
public class JobConfiguration {

	@Autowired
	private Scheduler scheduler; // 쿼츠 스케줄 객체
	
	@PostConstruct
	public void run() throws SchedulerException{
		JobDetail detail = runJobDetail(JobFetchSensor.class, new HashMap<>());
		
//		scheduler.scheduleJob(detail, runJobTrigger("0/10 * * * * ?"));
		
	}
	
	public Trigger runJobTrigger(String scheduleExp){
        	// 크론 스케줄 사용
		return TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
	}
	
	public JobDetail runJobDetail(Class job, Map params){
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.putAll(params);
	    // 스케줄 생성
		return newJob(job).usingJobData(jobDataMap).build();
	}
}
