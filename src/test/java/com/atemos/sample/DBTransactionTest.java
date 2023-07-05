package com.atemos.sample;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Ignore;
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
import com.atemos.sample.service.AMRInfoService;
import com.atemos.sample.service.BuildingService;
import com.atemos.sample.service.CustomerService;
import com.atemos.sample.service.LocationService;

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
	@DisplayName("AddCustomer Test")
	@Transactional
	public void addCustomer() throws Exception {
		Customer obj =new  Customer();
		 
		obj.setContractAmount(1);
		obj.setCostType("type1");
		obj.setEnergyType("energyType-1");
		obj.setName("name");
		obj.setIsAgree(true);
		obj.setTel("+82123412341234");
		customerService.onSave(obj);
	}
 }
