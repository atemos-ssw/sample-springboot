package com.atemos.sample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atemos.sample.custom.CommonResponse;
import com.atemos.sample.entity.AmrInfo;
import com.atemos.sample.entity.Customer;
import com.atemos.sample.repository.CustomerRepository;
import com.atemos.sample.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
@SuppressWarnings("rawtypes")
@RequestMapping("/api/v1/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getAll")
	public ResponseEntity getAllData() {
		List<Customer> list = customerService.onGetAll();
		CommonResponse response = new CommonResponse<>();
		response.setData(list);
		return new ResponseEntity(response, HttpStatus.OK);	 	
		
	}
	
	@GetMapping("/get/{customerId}")
	public ResponseEntity getData(@PathVariable("customerId") Integer customerId) {
		
		Optional<Customer> list = customerService.onGet(customerId);
		CommonResponse response = new CommonResponse<>();
		response.setData(list);
		return new ResponseEntity(response, HttpStatus.OK);	 	
	}
	
	@PostMapping("/create")
	public ResponseEntity create(@RequestBody Customer customer) {
		Customer c = customerService.onSave(customer);
		CommonResponse response = new CommonResponse<>();
		response.setData(c);
		return new ResponseEntity(response, HttpStatus.OK);	
	}
	
	@PostMapping("/update")
	public ResponseEntity update(@RequestBody Customer customer) {
		Customer c = customerService.onUpdate(customer);
		CommonResponse response = new CommonResponse<>();
		response.setData(c);
		return new ResponseEntity(response, HttpStatus.OK);	
	}
	
	

}
