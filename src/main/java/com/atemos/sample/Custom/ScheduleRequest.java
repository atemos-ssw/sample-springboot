package com.atemos.sample.Custom;

import lombok.Data;

@Data
public class ScheduleRequest {

	 private String jobName;
	 private String cronExpression;
	 private String items;
	 private String logicalId;
	 private String siteId;
	 private String groupId;
	    // 생성자, getter, setter 생략

	 
	 public boolean nullCheck() {
		 if(jobName == null || cronExpression == null || items== null || logicalId == null || siteId == null || groupId == null)
			 return false;
		 else
			 return true;
	 }
}
