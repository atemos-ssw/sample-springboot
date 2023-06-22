package com.atemos.sample.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atemos.sample.scheduler.JobFetchSensor;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Service
public class ScheduleService {

    private final Scheduler scheduler;

    @Autowired
    public ScheduleService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void scheduleJob(String jobName, String cronExpression, String items, 
    		String siteId, String groupId, String logicalId) throws SchedulerException {
    	
    	JobDataMap dataMap = new JobDataMap();
    	dataMap.put("items", items);
    	dataMap.put("siteId", siteId);
    	dataMap.put("groupId", groupId);
    	dataMap.put("logicalId", logicalId);
    	
        JobDetail jobDetail = JobBuilder.newJob(JobFetchSensor.class)
                .withIdentity(jobName)
                .setJobData(dataMap)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName)
                .withSchedule(cronSchedule(cronExpression))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
    
    public void pauseJob(String jobName) throws SchedulerException{
    	  scheduler.pauseJob(JobKey.jobKey(jobName));
    }

    public void pauseJobs() throws SchedulerException{
	  	  scheduler.pauseAll();
  }
    
    public void resumeJob(String jobName) throws SchedulerException{
    	scheduler.resumeJob(JobKey.jobKey(jobName));
    }
    public void unscheduleJob(String jobName) throws SchedulerException {
        scheduler.unscheduleJob(new TriggerKey(jobName));
    }
    

}