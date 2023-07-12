package com.atemos.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "AUTH_TOKEN") 
@Data
@ToString
public class AuthToken {

	@Id
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="EXPIRE")
	private String expire;
	
	
}
