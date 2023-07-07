package com.atemos.sample.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.atemos.sample.entity.Customer;
import com.atemos.sample.entity.Meter;
import com.atemos.sample.exception.ErrorCode;
import com.atemos.sample.exception.GlobalException;
import com.atemos.sample.repository.MeterRepository;

@Service
public class MeterService {

	private final MeterRepository meterRepository;
	
	@Autowired
	public MeterService(MeterRepository meterRepository) {
		// TODO Auto-generated constructor stub
		this.meterRepository = meterRepository;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Meter onSave(final Meter m) {
		Optional<Meter> optM =  meterRepository.findByMeterId(m.getMeterId());
		if(optM.isPresent()) {
			throw new GlobalException(m.toString(), ErrorCode.DATA_DUPLICATION);
		}
		Meter meter = meterRepository.save(m);
		return meter;
	}

	@Transactional(rollbackOn = Exception.class)
	public Meter onUpdate(final Meter m) {
		Optional<Meter> optM = meterRepository.findByMeterId(m.getMeterId());
		if(optM.isEmpty())
		{
			throw new GlobalException("Dat not founded " + m.getMeterId(), ErrorCode.NOT_FOUND);
		}
		Meter mm = meterRepository.save(m);
		return mm;
	}
	
	@Transactional
	public List<Meter> onGetAll() {
		List<Meter> list = meterRepository.findAll();
		return list;
	}
	
	@Transactional
	public Optional<Meter> onGet(Integer id){
		Optional<Meter> list = meterRepository.findByMeterId(id);
		return list;
	}
	
	
	
}
