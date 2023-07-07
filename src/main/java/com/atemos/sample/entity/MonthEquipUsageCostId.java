package com.atemos.sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class MonthEquipUsageCostId implements Serializable{
	@Column(name = "YEAR")
	private Integer year;

	@Column(name = "METER_ID")
	private Integer meterId;

	@Column(name = "MONTH")
	private Integer month;
}
