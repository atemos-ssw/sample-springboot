package com.atemos.sample.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
 
import lombok.Data;

@Entity
@Table(name="LOCATION")
@Data
public class Location {

	@Id
	@Column(name="LOCATION_ID", unique = true)
	private Integer locationId;
	
	@Column(name="NAME")
	private String name;
}
