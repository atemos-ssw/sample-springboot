package com.atemos.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "BUILDING")
@Data
@ToString
public class Building {

	@Id
	@Column(name = "BUILDING_ID")
	private Integer buildingId;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TEL")
	private String tel;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LOCATION_ID")
//	@JsonIgnore
	//	https://ahndding.tistory.com/24, 
	private Location location;

}
