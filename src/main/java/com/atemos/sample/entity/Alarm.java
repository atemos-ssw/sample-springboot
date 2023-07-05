package com.atemos.sample.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne; 
import javax.persistence.Table;
 
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="ALARM")
@Data
@ToString
public class Alarm {

	@Id
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "IS_APPLY")
    private Boolean isApply;

    @Column(name = "KWH_LIMIT")
    private Integer kwhLimit;

    @Column(name = "COST_LIMIT")
    private Integer costLimit;
	
}
