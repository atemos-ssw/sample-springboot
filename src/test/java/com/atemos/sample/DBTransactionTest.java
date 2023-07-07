package com.atemos.sample;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atemos.sample.custom.BasicResponse;
import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.entity.AmrInfo;
import com.atemos.sample.entity.Building;
import com.atemos.sample.entity.Customer;
import com.atemos.sample.entity.Location;
import com.atemos.sample.entity.Meter;
import com.atemos.sample.service.AMRInfoService;
import com.atemos.sample.service.BuildingService;
import com.atemos.sample.service.CustomerService;
import com.atemos.sample.service.LocationService;
import com.atemos.sample.service.MeterService;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
public class DBTransactionTest {


	@Autowired
    EntityManager entityManager;
	
	@Autowired
	BuildingService service;
	
	@Autowired
	LocationService locService;
	
	@Autowired
	AMRInfoService amrInfoService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	MeterService meterService;

	 
//	@Ignore
//	@DisplayName("addAMRInfo")
//	@Transactional
//	public void addAMRInfo() throws Exception {
//		AmrInfo obj =new AmrInfo();
//		
//		obj.setAddress("720A Đ. Điện Biên Phủ, Vinhomes Tân Cảng, Bình Thạnh, Thành phố Hồ Chí Minh");
//		obj.setPort(9000);
//		obj.setServerId(1);
//		obj.setStatus(false);
//		obj.setUrl("https://abcdefasdfasdfasdfasdfasdf.com");
//		 
//		amrInfoService.onSave(obj);
//		
//		Optional<AmrInfo> optAmrInfo = amrInfoService.onGetServerId(obj.getServerId());
//		AmrInfo obj2 = optAmrInfo.get();
//		
//		assertEquals(obj, obj2);
//				
//	}

	@Test 
	@DisplayName("addMeter Test")
	@Transactional
	public void addMeter() throws Exception {
		
		Customer obj =new  Customer();
		 
		obj.setContractAmount(1);
		obj.setCostType("t-1");
		obj.setEnergyType("e-1");
		obj.setName("name");
		obj.setIsAgree(true);
		obj.setTel("+82123412341234");
		customerService.onSave(obj);
		
		Meter m =new  Meter();
		Customer c = customerService.onGetAll().get(0);
		
		m.setMeterId(1);
		m.setCustomer(c);
		m.setMeterName("meter-01");
		m.setMeterType("t-1");
		m.setPeriod(30);
		m.setStatus(true);
		
		meterService.onSave(m);
		 
	}	
 }
