package com.atemos.sample.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MONTH_EQUIP_USAGE_COST") 
@Data
public class MonthEquipUsageCost {

	@EmbeddedId
	private MonthEquipUsageCostId id;

	@Column(name = "COST")
	private int cost;

	@Column(name = "KWH")
	private Float kwh;
}
