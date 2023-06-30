package com.atemos.sample.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="MONTH_USER_USAGE_CCOST")
 
@Data
public class MonthUserUsageCost {

	@EmbeddedId
    private MonthUserUsageCostId id;

    @Column(name = "COST")
    private int cost;

    @Column(name = "KWH")
    private Float kwh;
}
