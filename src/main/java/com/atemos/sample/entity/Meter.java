package com.atemos.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "METER") 
@Data
public class Meter {

	@Id 
	@Column(name = "METER_ID")
	private Integer meter_id;
	
	@Column(name="METER_TYPE")
	private String meterType;
	
	@Column(name="STATUS")
	private Boolean status;
	
	@Column(name="PERIOD")
	private int period;
	
	@Column(name="METER_NAME")
	private String meterName;
	
	@ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
	
}
