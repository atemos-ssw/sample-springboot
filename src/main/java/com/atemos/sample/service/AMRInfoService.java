package com.atemos.sample.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atemos.sample.custom.BasicResponse;
import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.entity.AmrInfo;
import com.atemos.sample.entity.Building;
import com.atemos.sample.exception.ErrorCode;
import com.atemos.sample.exception.GlobalException;
import com.atemos.sample.repository.AMRInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@SuppressWarnings("rawtypes")
public class AMRInfoService {

	private final AMRInfoRepository amrInfoRepository;
	
	@Autowired
	public AMRInfoService(AMRInfoRepository amrInfoRepository) {
		this.amrInfoRepository = amrInfoRepository;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public AmrInfo onSave(final AmrInfo amrInfo) {
		Optional<AmrInfo> optAmrInfo = amrInfoRepository.findByServerId(amrInfo.getServerId());
		if(optAmrInfo.isPresent()) {
			throw new GlobalException(amrInfo.toString()+" is duplicated", ErrorCode.DATA_DUPLICATION);
		} 
		amrInfoRepository.save(amrInfo); 
		log.info("[save] "+ amrInfo.toString()); 
		return amrInfo;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public AmrInfo onUpdate(final AmrInfo amrInfo) {
		Optional<AmrInfo> list = amrInfoRepository.findByServerId(amrInfo.getServerId());
		if(list.isEmpty()) {
			throw new GlobalException("Data is not founded" + amrInfo.toString(), ErrorCode.NOT_FOUND );
		}
		
		amrInfoRepository.save(amrInfo);
		log.info(String.format("[update] %s", amrInfo.toString()));
		return amrInfo;
	}
	
	@Transactional
	public List<AmrInfo> onGetAll() {
		List<AmrInfo> list = amrInfoRepository.findAll();
		return list;
	}

	@Transactional
	public Optional<AmrInfo> onGetServerId(int serverId) {
		// TODO Auto-generated method stub
		
		Optional<AmrInfo> amrInfo = amrInfoRepository.findByServerId(serverId);
		if(amrInfo.isEmpty()) {
			throw new GlobalException(String.format("serverId - %d not founded", serverId), ErrorCode.NOT_FOUND);
		}
		log.info(String.format("[select] %s", amrInfo.toString()));
		return amrInfo;
		
	} 
}
