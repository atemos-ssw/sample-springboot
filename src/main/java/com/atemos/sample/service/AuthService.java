package com.atemos.sample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.atemos.sample.entity.AuthToken;
import com.atemos.sample.entity.Customer;
import com.atemos.sample.exception.GlobalException;
import com.atemos.sample.repository.AuthRepository;
import com.atemos.sample.repository.CustomerRepository;
import com.atemos.sample.utils.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

@Service
public class AuthService {

	 private final AuthRepository authRepository;
	 private final CustomerRepository customerRepository;
	 
	 private final JwtUtil jwtUtil;
	 
	 @Autowired
	 public AuthService(AuthRepository authRepository, 
			 CustomerRepository customerRepository,
			 JwtUtil jwtUtil) {
		 this.authRepository = authRepository;
		 this.customerRepository = customerRepository;
		 this.jwtUtil = jwtUtil;
		// TODO Auto-generated constructor stub
	}

	public AuthToken createJWT(Integer id) {
		// TODO Auto-generated method stub
		Customer c = customerRepository.findByCustomerId(id)
		.orElseThrow(()->new GlobalException(String.format("user id %d not founded",id), null));
		
		String name = c.getName();
		
		String token = jwtUtil.generateToken(name);
		
		AuthToken authToken = new AuthToken();
		authToken.setCustomerId(id);
		authToken.setToken(token);
		authToken.setExpire(null);
		authToken = authRepository.save(authToken);
		
		return authToken;
	}
	
	public AuthToken hasToken(String token) {
		Optional<AuthToken> opt = authRepository.findByToken(token);
		return opt.get();
	}
}
