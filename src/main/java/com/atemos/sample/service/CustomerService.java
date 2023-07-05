package com.atemos.sample.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atemos.sample.custom.BasicResponse;
import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.entity.Building;
import com.atemos.sample.entity.Customer;
import com.atemos.sample.exception.ErrorCode;
import com.atemos.sample.exception.GlobalException;
import com.atemos.sample.repository.BuildingRepository;
import com.atemos.sample.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final BuildingRepository buildingRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository,BuildingRepository buildingRepository) {
		this.customerRepository = customerRepository;
		this.buildingRepository = buildingRepository;
	}

	@Transactional(rollbackOn = Exception.class)
	public Customer onSave(final Customer customer) {
		// TODO Auto-generated method stub
		Optional<Customer> optCustomer = customerRepository.findByCustomerId(customer.getCustomerId());
		if(optCustomer.isPresent()) {
			throw new GlobalException(customer.toString(), ErrorCode.DATA_DUPLICATION);
		}
		 
		Customer obj = customerRepository.save(customer);
		return obj;
	}

	@Transactional(rollbackOn = Exception.class)
	public Customer onUpdate(final Customer customer) {
		Optional<Customer> list = customerRepository.findByCustomerId(customer.getCustomerId());
		if(list.isEmpty())
		{
			throw new GlobalException(String.format("Data not founded", customer.toString()), ErrorCode.NOT_FOUND);
		}
		Customer updated = customerRepository.save(customer);
		return updated;
	}

	@Transactional
	public List<Customer> onGetAll() {
		List<Customer> list = customerRepository.findAll();
		return list;
	}
	
	@Transactional
	public Optional<Customer> onGet(Integer customerId){
		Optional<Customer> list = customerRepository.findByCustomerId(customerId);
		return list;
	}
	
	
}
