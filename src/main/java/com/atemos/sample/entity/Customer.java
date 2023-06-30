package com.atemos.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
 
import lombok.Data;

@Entity
@Table(name="CUSTOMER")
@Data
public class Customer {

	@Id
    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @ManyToOne
    @JoinColumn(name = "SERVER_ID")
    private AmrInfo amrInfo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_AGREE")
    private Boolean isAgree;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "ENERGY_TYPE")
    private String energyType;

    @Column(name = "CONTRACT_AMOUNT")
    private int contractAmount;

    @Column(name = "COST_TYPE")
    private String costType;

    @ManyToOne
    @JoinColumn(name = "BUILDING_ID")
    private Building building;
	
}
